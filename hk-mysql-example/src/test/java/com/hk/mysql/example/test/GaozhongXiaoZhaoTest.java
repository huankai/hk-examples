package com.hk.mysql.example.test;

import com.hk.commons.poi.excel.annotations.ReadExcelField;
import com.hk.commons.poi.excel.model.ReadParam;
import com.hk.commons.poi.excel.model.ReadResult;
import com.hk.commons.poi.excel.read.DomReadExcel;
import com.hk.commons.poi.excel.read.ReadableExcel;
import com.hk.commons.util.JsonUtils;
import com.hk.commons.util.StringUtils;
import com.hk.core.data.jdbc.JdbcSession;
import com.hk.core.test.BaseTest;
import com.hk.mysql.examples.MysqlExampleApplication;
import lombok.Data;
import org.apache.poi.xwpf.usermodel.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 非学业测试修改3稿\【3】高中二年级\【4】高中校长问卷
 *
 * @author huangkai
 * @date 2019-02-14 11:19
 */
@SpringBootTest(classes = {MysqlExampleApplication.class})
public class GaozhongXiaoZhaoTest extends BaseTest {

    @Autowired
    private JdbcSession jdbcSession;

    @Test
    public void gaozhongXiaozhang() throws IOException {
        ReadParam<ScoringRules> readParam = ReadParam.<ScoringRules>builder()
                .beanClazz(ScoringRules.class)
                .build();
        ReadableExcel<ScoringRules> readableExcel = new DomReadExcel<>(readParam);
        ReadResult<ScoringRules> readResult = readableExcel.read(new File("C:/Users/sjq-278/Desktop/非学业测试修改3稿/【3】高中二年级/【4】高中校长问卷/高中校长问卷计分规则.xlsx"));
        List<ScoringRules> data = readResult.getAllSheetData();

        FileInputStream fileInputStream = new FileInputStream("C:/Users/sjq-278/Desktop/非学业测试修改3稿/【3】高中二年级/【4】高中校长问卷/1【问卷测试题】高中校长问卷.docx");
        XWPFDocument document = new XWPFDocument(fileInputStream);


        // 获取所有段落
        List<QuestionnaireQuestion> questions = new ArrayList<>();
        List<XWPFParagraph> paragraphs = document.getParagraphs();
        int questionCode = 1;
        for (int i = 1, size = paragraphs.size(); i < size; i++) {//首行忽略
            XWPFParagraph xwpfParagraph = paragraphs.get(i);
            String text = StringUtils.trimToEmpty(xwpfParagraph.getParagraphText());
//            System.out.println("text: " + text);
            if (StringUtils.isNotEmpty(text) && !StringUtils.startWithNumber(text)) {
                if (!StringUtils.startWithLetter(text)) {
                    QuestionnaireQuestion question = new QuestionnaireQuestion();
                    if (questionCode == 1) {
                        question.setQuestionType("10"); // 第1 题是多选
                    }
                    question.setQuestionCode(String.valueOf(questionCode++));
                    question.setQuestionContent(text);
                    question.setQuestionId(UUID.randomUUID().toString());
                    questions.add(question);
                } else {
                    QuestionnaireQuestion question = questions.get(questions.size() - 1);
                    List<QuestionnaireAnswer> answers = question.getAnswers();
                    if (answers == null) {
                        answers = new ArrayList<>();
                    }
                    String[] answerArr = text.split(" {3}"); // 3 个空格分隔
                    QuestionnaireAnswer answer;
                    for (String answerItem : answerArr) {
                        String answerValue = StringUtils.trimToEmpty(answerItem);
                        if(StringUtils.isNotEmpty(answerValue)){
                            answer = new QuestionnaireAnswer();
                            answer.setAnswer(StringUtils.trimToEmpty(StringUtils.substring(answerValue, 2)));
                            answer.setAnswerCode(StringUtils.substring(answerValue, 0, 1));
                            answer.setQuestionId(question.getQuestionId());
                            answer.setAnswerId(UUID.randomUUID().toString());
                            answer.setScore(data.stream().filter(item -> StringUtils.equals(item.no, question.questionCode)).findFirst()
                                    .get().getScore(answer.answerCode));//分数
                            answers.add(answer);
                        }
                    }
                    question.setAnswers(answers);
                }
            }
        }
        System.out.println(JsonUtils.serialize(questions, true));
        System.out.println("-------------------->" + questions.size());


        //获取所有表格
        Map<Integer, QuestionnaireQuestion> tableQuestions = new HashMap<>();
        Map<Integer, List<QuestionnaireAnswer>> answers = new LinkedHashMap<>();
        for (XWPFTable table : document.getTables()) {
            int rowIndex = 0;
            for (XWPFTableRow row : table.getRows()) {
                List<XWPFTableCell> tableCells = row.getTableCells();
                for (int i = 0, size = tableCells.size(); i < size; i++) {
                    XWPFTableCell cell = tableCells.get(i);
                    String text = cell.getText();
                    if (i == 0) {
                        if (StringUtils.isNumber(text)) {
                            QuestionnaireQuestion question = new QuestionnaireQuestion();
                            question.setQuestionCode(text); // 题号
                            question.setRow(rowIndex);
                            question.setQuestionId(UUID.randomUUID().toString());
                            tableQuestions.put(rowIndex, question);
                        }
                    } else if (i == 1) {
                        if (StringUtils.isNotEmpty(text)) {
                            QuestionnaireQuestion question = tableQuestions.get(rowIndex);
                            question.setQuestionContent(text);
                        }
                    } else {
                        if (StringUtils.isNotEmpty(text)) {
                            List<QuestionnaireAnswer> rowAnswers = answers.get(rowIndex);
                            if (null == rowAnswers) {
                                rowAnswers = new ArrayList<>();
                            }
                            QuestionnaireAnswer answer = new QuestionnaireAnswer();
                            answer.setAnswer(StringUtils.trimToEmpty(StringUtils.substring(text, 1)));
                            answer.setAnswerCode(StringUtils.substring(text, 0, 1));
                            rowAnswers.add(answer);
                            answers.put(rowIndex, rowAnswers);
                        }
                    }

                }
                rowIndex++;
            }
        }
        List<QuestionnaireQuestion> values = new ArrayList<>(tableQuestions.values());
        for (int i = 0, size = values.size(); i < size; i++) {
            QuestionnaireQuestion question = values.get(i);
            List<QuestionnaireAnswer> answerList = answers.get(getIndex(answers.keySet(), question.row));
            List<QuestionnaireAnswer> answers1 = new ArrayList<>();
            QuestionnaireAnswer item;
            for (QuestionnaireAnswer answer : answerList) {
                item = new QuestionnaireAnswer();
                item.setAnswer(answer.getAnswer());
                item.setAnswerCode(answer.getAnswerCode());
                item.setQuestionId(question.getQuestionId());
                item.setAnswerId(UUID.randomUUID().toString());
                item.setScore(data.stream().filter(item_ -> StringUtils.equals(item_.no, question.questionCode)).findFirst()
                        .get().getScore(answer.answerCode));//分数
                answers1.add(item);
            }
            question.setAnswers(answers1);
        }
        System.out.println(JsonUtils.serialize(values, true));
        System.out.println(values.size());
        jdbcSession.batchUpdate("INSERT INTO `et_questionnaire_question_temp`(`question_id`, `questionnaire_id`, `question_code`,`question_content`, `question_type`,`cfgtype`, `last_up_time`, `create_time`) VALUES " +
                "(:questionId,:questionnaireId,:questionCode,:questionContent,:questionType,:cfgtype,:lastUpTime,:createTime)", questions);
        for (QuestionnaireQuestion question : questions) {
            jdbcSession.batchUpdate("INSERT INTO `et_questionnaire_answer_temp`(`answer_id`, `question_id`, `questionnaire_id`, `answer_code`, `answer`,`score`, `last_up_time`, `create_time`) VALUES " +
                    "(:answerId,:questionId,:questionnaireId,:answerCode,:answer,:score,:lastUpTime,:createTime)", question.getAnswers());

        }

        jdbcSession.batchUpdate("INSERT INTO `et_questionnaire_question_temp`(`question_id`, `questionnaire_id`, `question_code`,`question_content`, `question_type`,`cfgtype`, `last_up_time`, `create_time`) VALUES " +
                "(:questionId,:questionnaireId,:questionCode,:questionContent,:questionType,:cfgtype,:lastUpTime,:createTime)", values);

        for (QuestionnaireQuestion question : values) {
            jdbcSession.batchUpdate("INSERT INTO `et_questionnaire_answer_temp`(`answer_id`, `question_id`, `questionnaire_id`, `answer_code`, `answer`,`score`, `last_up_time`, `create_time`) VALUES " +
                    "(:answerId,:questionId,:questionnaireId,:answerCode,:answer,:score,:lastUpTime,:createTime)", question.getAnswers());

        }

    }

    private static Integer getIndex(Collection<Integer> coll, Integer value) {
        List<Integer> integers = coll.stream().filter(item -> item <= value).collect(Collectors.toList());
        return integers.get(integers.size() - 1);
    }


    /**
     * 计分规则
     */
    @Data
    private static class ScoringRules {

        @ReadExcelField(start = 2)
        private String no;

        @ReadExcelField(start = 4)
        private String a;

        @ReadExcelField(start = 5)
        private String b;

        @ReadExcelField(start = 6)
        private String c;

        @ReadExcelField(start = 7)
        private String d;

        @ReadExcelField(start = 8)
        private String e;

        @ReadExcelField(start = 9)
        private String f;

        @ReadExcelField(start = 10)
        private String g;

        @ReadExcelField(start = 11)
        private String h;

        @ReadExcelField(start = 12)
        private String i;

        @ReadExcelField(start = 13)
        private String j;

        @ReadExcelField(start = 14)
        private String k;

        @ReadExcelField(start = 15)
        private String l;

        @ReadExcelField(start = 16)
        private String m;

        @ReadExcelField(start = 17)
        private String n;


        public Double getScore(String code) {
            code = code.toUpperCase();
            String value;
            switch (code) {
                case "A":
                    value = a;
                    break;
                case "B":
                    value = b;
                    break;
                case "C":
                    value = c;
                    break;
                case "D":
                    value = d;
                    break;
                case "E":
                    value = e;
                    break;
                case "F":
                    value = f;
                    break;
                case "G":
                    value = g;
                    break;
                case "H":
                    value = h;
                    break;
                case "I":
                    value = i;
                    break;
                case "J":
                    value = j;
                    break;
                case "K":
                    value = k;
                    break;
                case "L":
                    value = l;
                    break;
                case "M":
                    value = m;
                    break;
                case "N":
                    value = n;
                    break;
                default:
                    value = "0";
            }
            try {
                return Double.parseDouble(value);
            } catch (Exception e) {
                return 0D;
            }
        }

    }

    private static final String QUESTIONNAIRE_ID = "1d3a9553-03bf-46a8-ab52-8e3e038327c5";

    /**
     * 卷题目 : et_questionnaire_question
     */
    @Data
    private static class QuestionnaireQuestion {

        private String questionId;

        private String questionnaireId = QUESTIONNAIRE_ID;

        private String questionCode;


        private String questionContent;

        /**
         * 单选
         */
        private String questionType = "01";

        /**
         *
         */
        private String cfgtype = "0";

        private Integer row;

        private Date lastUpTime = new Date();

        private Date createTime = new Date();

        private List<QuestionnaireAnswer> answers;
    }

    /**
     * 卷答案: et_questionnaire_answer
     */
    @Data
    private static class QuestionnaireAnswer {


        private String answerId;

        private String questionnaireId = QUESTIONNAIRE_ID;

        private String questionId;

        private String answerCode;

        private String answer;

        private Double score = 0D;

        private Date lastUpTime = new Date();

        private Date createTime = new Date();

    }
}
