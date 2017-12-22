package com.hk.example.write;

import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 考试成绩导出
 * 
 * @author huangkai
 * @date 2017年12月22日下午2:24:26
 */
public class TeamScoreWriteTest {

	@Test
	public void writeTest() {

		for (int classIndex = 1; classIndex <= 10; classIndex++) {
			List<TeamScoreVo> classOneList = Lists.newArrayList();
			for (int studentIndex = 0; studentIndex < 20; studentIndex++) {
				TeamScoreVo score = new TeamScoreVo();
				score.setStudentNo("student"+ studentIndex);
				score.setStudentName("studentName" + studentIndex);
				classOneList.add(score);
			}
		}
	}

	private class TeamScoreVo {

		/**
		 * 学生考试编号
		 */
		private String studentNo;

		/**
		 * 学生名称
		 */
		private String studentName;

		/**
		 * 学生考试成绩
		 */
		private Map<String, Double> subjectScores = Maps.newLinkedHashMap();

		/**
		 * 总分数
		 */
		private double totalScore;

		/**
		 * 班级排名
		 */
		private int classSort;

		/**
		 * 年级排名
		 */
		private int gradeSort;

		public String getStudentNo() {
			return studentNo;
		}

		public void setStudentNo(String studentNo) {
			this.studentNo = studentNo;
		}

		public String getStudentName() {
			return studentName;
		}

		public void setStudentName(String studentName) {
			this.studentName = studentName;
		}

		public Map<String, Double> getSubjectScores() {
			return subjectScores;
		}

		public void setSubjectScores(Map<String, Double> subjectScores) {
			this.subjectScores = subjectScores;
		}

		public double getTotalScore() {
			return totalScore;
		}

		public void setTotalScore(double totalScore) {
			this.totalScore = totalScore;
		}

		public int getClassSort() {
			return classSort;
		}

		public void setClassSort(int classSort) {
			this.classSort = classSort;
		}

		public int getGradeSort() {
			return gradeSort;
		}

		public void setGradeSort(int gradeSort) {
			this.gradeSort = gradeSort;
		}
	}
}
