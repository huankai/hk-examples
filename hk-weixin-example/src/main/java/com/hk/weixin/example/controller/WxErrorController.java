package com.hk.weixin.example.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Binary Wang(https://github.com/binarywang)
 */
@Controller
public class WxErrorController implements ErrorController {

	private static final Logger logger = LoggerFactory.getLogger(WxErrorController.class);

	private final static String ERROR_PATH = "/error";

	/**
	 * Error Attributes in the Application
	 */
	@Autowired
	private ErrorAttributes errorAttributes;

	/**
	 * Controller for the Error Controller
	 *
	 * @param errorAttributes
	 */

	public WxErrorController(ErrorAttributes errorAttributes) {
		this.errorAttributes = errorAttributes;
	}

	public WxErrorController() {
	}

	/**
	 * Supports the HTML Error View
	 *
	 * @param request
	 */
	@RequestMapping(value = ERROR_PATH, produces = "text/html")
	public ModelAndView errorHtml(HttpServletRequest request) {
		return new ModelAndView("error", this.getErrorAttributes(request, false));
	}

	/**
	 * Supports other formats like JSON, XML
	 *
	 * @param request
	 */
	@RequestMapping(value = ERROR_PATH)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
		Map<String, Object> body = this.getErrorAttributes(request, this.getTraceParameter(request));
		HttpStatus status = this.getStatus(request);
		return new ResponseEntity<>(body, status);
	}

	@Override
	public String getErrorPath() {
		return ERROR_PATH;
	}

	private boolean getTraceParameter(HttpServletRequest request) {
		String parameter = request.getParameter("trace");
		if (parameter == null) {
			return false;
		}

		return !"false".equals(parameter.toLowerCase());
	}

	private Map<String, Object> getErrorAttributes(HttpServletRequest request, boolean includeStackTrace) {
		RequestAttributes requestAttributes = new ServletRequestAttributes(request);
		Map<String, Object> map = this.errorAttributes.getErrorAttributes(requestAttributes, includeStackTrace);
		logger.error("map is [{}]", map);
		String url = request.getRequestURL().toString();
		map.put("URL", url);
		logger.error("[error info]: status-{}, request url-{}", map.get("status"), url);
		return map;
	}

	private HttpStatus getStatus(HttpServletRequest request) {
		Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
		if (statusCode != null) {
			return HttpStatus.valueOf(statusCode);
		}

		return HttpStatus.INTERNAL_SERVER_ERROR;
	}

}
