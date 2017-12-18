不要双击 ngrok.exe,打开shell或cmd ,进入当前目录 ，运行ngrok -config ngrok.cfg -subdomain abdomain 8080 
如果运行失败，请更换abdomain为其它字符串，直至连接成功；

配置微信公众号中的接口地址：http://abdomain.tunnel.qydev.com/wechat/portal （注意my-domain要跟上面的一致，需要符合微信官方的要求）；