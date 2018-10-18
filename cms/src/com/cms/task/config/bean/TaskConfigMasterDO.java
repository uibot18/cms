package com.cms.task.config.bean;

import java.util.ArrayList;
import java.util.List;

public class TaskConfigMasterDO { 

	private int taskConfigId=0;
	private int processId=0;
	private String taskConfigName="";
	private int exeOrder;
	private String department="";
	private String designation="";
	private String empId="";
	private int ticketDuration=0;
	private String ticketDurationUom="na";
	private String configType="";
	private int dailyEveryDay=0;
	private boolean boolDailyEveryWeekDay;
	private int weeklyEveryWeek=0;
	private String weeklyWeekDay="";
	private boolean boolMonthlyDaySpecfic=false;
	private int monthlyEveryMonth=0;
	private int monthlyEveryMonthDay=0;
	private String monthlyEveryWeek="na";
	private String monthlyEveryWeekWeekday="na";
	private boolean boolYearlyYearSpecific;
	private int yearlyEveryYear=0;
	private String yearlyEveryWeek="na";
	private String yearlyEveryWeekWeek="na";
	private int yearlyEveryMonth;
	private String holidayIds="";
	private int endAfterNoOfRec=0;
	private boolean boolNoEndDate;
	private String startTime="";
	private int duration=0;
	private String durationType="na";
	private int taskExeUnit=0;
	private String taskExeUnitUom="na";
	private String refTaskConfigType="na";
	private int refTaskConfigId=0;
	private boolean boolDeleteStatus;
	private String createdUser="";
	private String createdDate="";
	private String updateUser="";
	private String updateDate="";
	
	List<TaskConfigEscalationChildDO> escalationChildList=new ArrayList<TaskConfigEscalationChildDO>();
	
	public int getTaskConfigId() {
		return taskConfigId;
	}
	public void setTaskConfigId(int taskConfigId) {
		this.taskConfigId = taskConfigId;
	}
	public int getProcessId() {
		return processId;
	}
	public void setProcessId(int processId) {
		this.processId = processId;
	}
	public String getTaskConfigName() {
		return taskConfigName;
	}
	public void setTaskConfigName(String taskConfigName) {
		this.taskConfigName = taskConfigName;
	}
	public int getExeOrder() {
		return exeOrder;
	}
	public void setExeOrder(int exeOrder) {
		this.exeOrder = exeOrder;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public int getTicketDuration() {
		return ticketDuration;
	}
	public void setTicketDuration(int ticketDuration) {
		this.ticketDuration = ticketDuration;
	}
	public String getTicketDurationUom() {
		return ticketDurationUom;
	}
	public void setTicketDurationUom(String ticketDurationUom) {
		this.ticketDurationUom = ticketDurationUom;
	}
	public String getConfigType() {
		return configType;
	}
	public void setConfigType(String configType) {
		this.configType = configType;
	}
	public int getDailyEveryDay() {
		return dailyEveryDay;
	}
	public void setDailyEveryDay(int dailyEveryDay) {
		this.dailyEveryDay = dailyEveryDay;
	}
	public boolean isBoolDailyEveryWeekDay() {
		return boolDailyEveryWeekDay;
	}
	public void setBoolDailyEveryWeekDay(boolean boolDailyEveryWeekDay) {
		this.boolDailyEveryWeekDay = boolDailyEveryWeekDay;
	}
	public int getWeeklyEveryWeek() {
		return weeklyEveryWeek;
	}
	public void setWeeklyEveryWeek(int weeklyEveryWeek) {
		this.weeklyEveryWeek = weeklyEveryWeek;
	}
	public String getWeeklyWeekDay() {
		return weeklyWeekDay;
	}
	public void setWeeklyWeekDay(String weeklyWeekDay) {
		this.weeklyWeekDay = weeklyWeekDay;
	}
	public boolean isBoolMonthlyDaySpecfic() {
		return boolMonthlyDaySpecfic;
	}
	public void setBoolMonthlyDaySpecfic(boolean boolMonthlyDaySpecfic) {
		this.boolMonthlyDaySpecfic = boolMonthlyDaySpecfic;
	}
	public int getMonthlyEveryMonth() {
		return monthlyEveryMonth;
	}
	public void setMonthlyEveryMonth(int monthlyEveryMonth) {
		this.monthlyEveryMonth = monthlyEveryMonth;
	}
	public int getMonthlyEveryMonthDay() {
		return monthlyEveryMonthDay;
	}
	public void setMonthlyEveryMonthDay(int monthlyEveryMonthDay) {
		this.monthlyEveryMonthDay = monthlyEveryMonthDay;
	}
	public String getMonthlyEveryWeek() {
		return monthlyEveryWeek;
	}
	public void setMonthlyEveryWeek(String monthlyEveryWeek) {
		this.monthlyEveryWeek = monthlyEveryWeek;
	}
	public String getMonthlyEveryWeekWeekday() {
		return monthlyEveryWeekWeekday;
	}
	public void setMonthlyEveryWeekWeekday(String monthlyEveryWeekWeekday) {
		this.monthlyEveryWeekWeekday = monthlyEveryWeekWeekday;
	}
	public boolean isBoolYearlyYearSpecific() {
		return boolYearlyYearSpecific;
	}
	public void setBoolYearlyYearSpecific(boolean boolYearlyYearSpecific) {
		this.boolYearlyYearSpecific = boolYearlyYearSpecific;
	}
	public int getYearlyEveryYear() {
		return yearlyEveryYear;
	}
	public void setYearlyEveryYear(int yearlyEveryYear) {
		this.yearlyEveryYear = yearlyEveryYear;
	}
	public String getYearlyEveryWeek() {
		return yearlyEveryWeek;
	}
	public void setYearlyEveryWeek(String yearlyEveryWeek) {
		this.yearlyEveryWeek = yearlyEveryWeek;
	}
	public String getYearlyEveryWeekWeek() {
		return yearlyEveryWeekWeek;
	}
	public void setYearlyEveryWeekWeek(String yearlyEveryWeekWeek) {
		this.yearlyEveryWeekWeek = yearlyEveryWeekWeek;
	}
	public int getYearlyEveryMonth() {
		return yearlyEveryMonth;
	}
	public void setYearlyEveryMonth(int yearlyEveryMonth) {
		this.yearlyEveryMonth = yearlyEveryMonth;
	}
	public String getHolidayIds() {
		return holidayIds;
	}
	public void setHolidayIds(String holidayIds) {
		this.holidayIds = holidayIds;
	}
	public int getEndAfterNoOfRec() {
		return endAfterNoOfRec;
	}
	public void setEndAfterNoOfRec(int endAfterNoOfRec) {
		this.endAfterNoOfRec = endAfterNoOfRec;
	}
	public boolean isBoolNoEndDate() {
		return boolNoEndDate;
	}
	public void setBoolNoEndDate(boolean boolNoEndDate) {
		this.boolNoEndDate = boolNoEndDate;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public String getDurationType() {
		return durationType;
	}
	public void setDurationType(String durationType) {
		this.durationType = durationType;
	}
	public int getTaskExeUnit() {
		return taskExeUnit;
	}
	public void setTaskExeUnit(int taskExeUnit) {
		this.taskExeUnit = taskExeUnit;
	}
	public String getTaskExeUnitUom() {
		return taskExeUnitUom;
	}
	public void setTaskExeUnitUom(String taskExeUnitUom) {
		this.taskExeUnitUom = taskExeUnitUom;
	}
	public String getRefTaskConfigType() {
		return refTaskConfigType;
	}
	public void setRefTaskConfigType(String refTaskConfigType) {
		this.refTaskConfigType = refTaskConfigType;
	}
	public int getRefTaskConfigId() {
		return refTaskConfigId;
	}
	public void setRefTaskConfigId(int refTaskConfigId) {
		this.refTaskConfigId = refTaskConfigId;
	}
	public boolean isBoolDeleteStatus() {
		return boolDeleteStatus;
	}
	public void setBoolDeleteStatus(boolean boolDeleteStatus) {
		this.boolDeleteStatus = boolDeleteStatus;
	}
	public String getCreatedUser() {
		return createdUser;
	}
	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	public List<TaskConfigEscalationChildDO> getEscalationChildList() {
		return escalationChildList;
	}
	public void setEscalationChildList(List<TaskConfigEscalationChildDO> escalationChildList) {
		this.escalationChildList = escalationChildList;
	}
	@Override
	public String toString() {
		return "TaskConfigMasterDO [taskConfigId=" + taskConfigId + ", processId=" + processId + ", taskConfigName="
				+ taskConfigName + ", exeOrder=" + exeOrder + ", department=" + department + ", designation="
				+ designation + ", empId=" + empId + ", configType=" + configType + ", dailyEveryDay=" + dailyEveryDay
				+ ", boolDailyEveryWeekDay=" + boolDailyEveryWeekDay + ", weeklyEveryWeek=" + weeklyEveryWeek
				+ ", weeklyWeekDay=" + weeklyWeekDay + ", boolMonthlyDaySpecfic=" + boolMonthlyDaySpecfic
				+ ", monthlyEveryMonth=" + monthlyEveryMonth + ", monthlyEveryMonthDay=" + monthlyEveryMonthDay
				+ ", monthlyEveryWeek=" + monthlyEveryWeek + ", monthlyEveryWeekWeekday=" + monthlyEveryWeekWeekday
				+ ", boolYearlyYearSpecific=" + boolYearlyYearSpecific + ", yearlyEveryYear=" + yearlyEveryYear
				+ ", yearlyEveryWeek=" + yearlyEveryWeek + ", yearlyEveryWeekWeek=" + yearlyEveryWeekWeek
				+ ", yearlyEveryMonth=" + yearlyEveryMonth + ", holidayIds=" + holidayIds + ", endAfterNoOfRec="
				+ endAfterNoOfRec + ", boolNoEndDate=" + boolNoEndDate + ", startTime=" + startTime + ", duration="
				+ duration + ", durationType=" + durationType + ", taskExeUnit=" + taskExeUnit + ", taskExeUnitUom="
				+ taskExeUnitUom + ", refTaskConfigType=" + refTaskConfigType + ", refTaskConfigId=" + refTaskConfigId
				+ ", boolDeleteStatus=" + boolDeleteStatus + ", createdUser=" + createdUser + ", createdDate="
				+ createdDate + ", updateUser=" + updateUser + ", updateDate=" + updateDate + ", escalationChildList="
				+ escalationChildList + "]";
	}
	
}