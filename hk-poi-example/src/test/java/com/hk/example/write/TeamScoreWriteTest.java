package com.hk.example.write;

/**
 * 考试成绩导出
 * 
 * @author huangkai
 * @date 2017年12月22日下午2:24:26
 */
public class TeamScoreWriteTest {

//	@Test
//	@SuppressWarnings("unchecked")
//	public void writeTest() throws FileNotFoundException {
//		List<String> headerList = Lists.newArrayList("考号", "姓名", "语文", "数学", "英语", "总分", "班排", "年排");
//		List<List<List<Object>>> data = Lists.newArrayList();
//		for (int classIndex = 1; classIndex <= 10; classIndex++) {
//			List<List<Object>> classList = Lists.newArrayList();
//			for (int studentIndex = 0; studentIndex < 20; studentIndex++) {
//				List<Object> rowdata = Lists.newArrayList(studentIndex + 1, "studentIndex" + studentIndex, 80, 90, 75,
//						245, 20, 280);
//				classList.add(rowdata);
//			}
//			data.add(classList);
//		}
//		WriteableParams<List<List<Object>>> params = WriteableParams.newWriteableParamsBuilder()
//				.setSheetName("年级考试成绩表")//
//				.setBeanClazz(List.class)//
//				.setData(data)//
//				.setTitleRowHeight(30)//
//				.setDataRowHeight(25)
//				.build();
//		WriteableExcel<List<List<Object>>> writeableExcel = new DefaultSXSSFWriteableExcel<List<List<Object>>>(
//				new DefaultWriteHandler<List<List<Object>>>() {
//
//					@Override
//					protected void writeSheet(Sheet sheet) {
//						// 标题、合并行样式
//						CustomCellStyle mergingCellStyle = new CustomCellStyle();
//						mergingCellStyle.setBorder(BorderStyle.THIN);
//						mergingCellStyle.setBold(true);
//						CellStyle classNameStyle = mergingCellStyle.toCellStyle(workbook, DataFormat.TEXT_FORMAT);
//
//						// 标题样式与数据样式
//						ColumnStyle heaserCustomstyle = new ColumnStyle();
//						heaserCustomstyle.setBorder(BorderStyle.THIN);
//						heaserCustomstyle.setWidth(15);
//						heaserCustomstyle.setBold(true);
//						CellStyle heaserCellStyle = heaserCustomstyle.toCellStyle(workbook, DataFormat.TEXT_FORMAT);
//						
//						CustomCellStyle bodyCustomStyle = new CustomCellStyle();
//						bodyCustomStyle.setBorder(BorderStyle.THIN);
//						CellStyle bodyCellStyle = bodyCustomStyle.toCellStyle(workbook, DataFormat.TEXT_FORMAT);
//
//						List<String> staticNames = Lists.newArrayList("最低分","最高分","平均分","及格率","优秀率");
//						int rowIndex = 0;
//						int classIndex = 1;
//						for (List<List<Object>> classData : params.getData()) {
//							Row classInfoRow = sheet.createRow(rowIndex++);
//							classInfoRow.setHeightInPoints(50);
//							
//							Row headerRow = sheet.createRow(rowIndex++);
//							headerRow.setHeightInPoints(params.getTitleRowHeight());
//							int rowColumnIndex = 0;
//							for (String header : headerList) {
//								Cell classInfoCell = classInfoRow.createCell(rowColumnIndex);
//								classInfoCell.setCellStyle(classNameStyle);
//
//								sheet.setColumnWidth(rowColumnIndex, heaserCustomstyle.getWidth());
//								Cell cell = headerRow.createCell(rowColumnIndex++, CellType.STRING);
//								setCellValue(cell, null, header);
//								cell.setCellStyle(heaserCellStyle);
//							}
//							mergingCells(sheet, rowIndex - 2, rowIndex - 2, 0, headerList.size() - 1);
//							classInfoRow.getCell(0).setCellValue((classIndex++) + "年级分数");
//							for (List<Object> studentItem : classData) {
//								Row dataRow = sheet.createRow(rowIndex++);
//								dataRow.setHeightInPoints(params.getDataRowHeight());
//								int dataColumnIndex = 0;
//								for (Object data : studentItem) {
//									Cell cell = dataRow.createCell(dataColumnIndex++);
//									setCellValue(cell, null, data);
//									cell.setCellStyle(bodyCellStyle);
//								}
//							}
//							for (int classStaticRowIndex = 0; classStaticRowIndex < 5; classStaticRowIndex++) {
//								Row staticRow = sheet.createRow(rowIndex);// 最高分行
//								staticRow.setHeightInPoints(params.getDataRowHeight());
//								for (int i = 0; i < headerList.size(); i++) {
//									Cell staticsCell = staticRow.createCell(i);
//									staticsCell.setCellStyle(bodyCellStyle);
//									if (i == 2) {// 语文
//										staticsCell.setCellValue(350);
//									}
//									if (i == 3) {// 数学
//										staticsCell.setCellValue(450);
//									}
//									if (i == 4) {// 英语
//										staticsCell.setCellValue(550);
//									}
//								}
//								mergingCells(sheet, rowIndex, rowIndex, 0, 1);
//								staticRow.getCell(0).setCellValue(staticNames.get(classStaticRowIndex));
//								rowIndex++;
//							}
//
//						}
//					}
//				});
//		writeableExcel.write(params, new FileOutputStream(new File("C:/Users/sjq-278/Desktop/teamscoress.xlsx")));
//	}

//	public static class TeamScoreVo {
//
//		/**
//		 * 学生考试编号
//		 */
//		@ExportExcel(index = 0, value = "")
//		private String studentNo;
//
//		/**
//		 * 学生名称
//		 */
//		private String studentName;
//
//		/**
//		 * 学生考试成绩
//		 */
//		private Map<String, Double> subjectScores = Maps.newLinkedHashMap();
//
//		/**
//		 * 总分数
//		 */
//		private double totalScore;
//
//		/**
//		 * 班级排名
//		 */
//		private int classSort;
//
//		/**
//		 * 年级排名
//		 */
//		private int gradeSort;
//
//		/**
//		 * @return the studentNo
//		 */
//		public String getStudentNo() {
//			return studentNo;
//		}
//
//		/**
//		 * @param studentNo
//		 *            the studentNo to set
//		 */
//		public void setStudentNo(String studentNo) {
//			this.studentNo = studentNo;
//		}
//
//		/**
//		 * @return the studentName
//		 */
//		public String getStudentName() {
//			return studentName;
//		}
//
//		/**
//		 * @param studentName
//		 *            the studentName to set
//		 */
//		public void setStudentName(String studentName) {
//			this.studentName = studentName;
//		}
//
//		/**
//		 * @return the subjectScores
//		 */
//		public Map<String, Double> getSubjectScores() {
//			return subjectScores;
//		}
//
//		/**
//		 * @param subjectScores
//		 *            the subjectScores to set
//		 */
//		public void setSubjectScores(Map<String, Double> subjectScores) {
//			this.subjectScores = subjectScores;
//		}
//
//		/**
//		 * @return the totalScore
//		 */
//		public double getTotalScore() {
//			return totalScore;
//		}
//
//		/**
//		 * @param totalScore
//		 *            the totalScore to set
//		 */
//		public void setTotalScore(double totalScore) {
//			this.totalScore = totalScore;
//		}
//
//		/**
//		 * @return the classSort
//		 */
//		public int getClassSort() {
//			return classSort;
//		}
//
//		/**
//		 * @param classSort
//		 *            the classSort to set
//		 */
//		public void setClassSort(int classSort) {
//			this.classSort = classSort;
//		}
//
//		/**
//		 * @return the gradeSort
//		 */
//		public int getGradeSort() {
//			return gradeSort;
//		}
//
//		/**
//		 * @param gradeSort
//		 *            the gradeSort to set
//		 */
//		public void setGradeSort(int gradeSort) {
//			this.gradeSort = gradeSort;
//		}
//
//	}
}
