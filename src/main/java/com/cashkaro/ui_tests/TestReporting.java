package com.cashkaro.ui_tests;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class TestReporting
{
  private static final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
  private static final SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-yyyy");
  private static final SimpleDateFormat DATE_FORMAT_NOW = new SimpleDateFormat("yyyyMMddHHmmss");
  private static List<String> testCaseDescriptionList = new ArrayList();
  private static List<String> testCaseStartTimeList = new ArrayList();
  private static List<String> testCaseEndTimeList = new ArrayList();
  private static List<String> testCaseElapsedTimeList = new ArrayList();
  private static List<String> testCaseStatusList = new ArrayList();
  private static List<String> snapShotPathList = new ArrayList();
  public static long testCaseStartTime;
  public static long testCaseStopTime;
  public static long testCaseElapsedTime;
  public static String strCaseStartTime;
  public static String strCaseStopTime;
  private static List<String> testCaseNameList = new ArrayList();
  private static List<String> testCaseFileList = new ArrayList();
  private static List<String> stepList = new ArrayList();
  private static List<String> stepStatusList = new ArrayList();
  public static String suiteName;
  public static String TestName;
  private static Map<String, String> statusMap = new HashMap();
  public static File suiteFolder;
  private static File snapShotFolder;
  private static File testFolder;
  private static File suiteFile;
  private static String suiteFilePath;
  private static String testCaseReportsFolder = "Test";
  private static String snapShotsFolder = "Snapshots";
  private static File DataFolder;
  private static String testDataFolder = "TestData";
  private static long testStepTempTime;
  private static List<String> testStepElapsedTimeList = new ArrayList();

  public static void captureTestCaseStartTime()
  {
    testCaseStartTime = System.currentTimeMillis();
    strCaseStartTime = sdf.format(new Date());
    testCaseStartTimeList.add(strCaseStartTime);
    testStepTempTime = testCaseStartTime;
  }

  public static void captureTestCaseEndTime()
  {
    testCaseStopTime = System.currentTimeMillis();
    strCaseStopTime = sdf.format(new Date());
    testCaseEndTimeList.add(strCaseStopTime);
    calculateElapsedTime();
  }

  private static void calculateElapsedTime()
  {
    testCaseElapsedTime = testCaseStopTime - testCaseStartTime;
    int i = 0;
    int j = (int)(testCaseElapsedTime / 1000L);
    String str = String.valueOf(j);
    if (j >= 60)
    {
      i = j / 60;
      j %= 60;
      str = String.valueOf(j);
    }
    if (j <= 9)
      str = "0" + String.valueOf(j);
    testCaseElapsedTimeList.add(String.valueOf(i) + ":" + str);
  }

  public static void generateHTMLReportForTestSuites()
  {
    FileWriter localFileWriter = null;
    int i = 1;
    int j = 0;
    int k = 0;
    try
    {
      localFileWriter = new FileWriter(suiteFile, true);
      localFileWriter.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\"> ");
      localFileWriter.write("<html>");
      localFileWriter.write("<head>");
      localFileWriter.write("<body bgcolor='#E6E6FA'>");
      localFileWriter.write("<div style='background-color:#FFFF00'>");
      localFileWriter.write("<h3>Operating System : " + System.getProperty("os.name") + "</h3>");
      localFileWriter.write("<h3>Date : " + sdf1.format(new Date()) + "</h3>");
      localFileWriter.write("<h3>Suite Name : " + suiteName + "</h3>");
      localFileWriter.write("</div>");
      localFileWriter.write("<table border='1' width='100%'>");
      localFileWriter.write("<tr style='background-color:#FF8000'>");
      localFileWriter.write("<th>SR.No</th>");
      localFileWriter.write("<th>Test Case Name</th>");
      localFileWriter.write("<th>Test Case Description</th>");
      localFileWriter.write("<th>Test Case Start Time</th>");
      localFileWriter.write("<th>Test Case End Time</th>");
      localFileWriter.write("<th>Test Case Elapsed Time<br/>(mm:ss)</th>");
      localFileWriter.write("<th>Test Case Status</th>");
      localFileWriter.write("</tr>");
      for (int m = 0; m < testCaseNameList.size(); m++)
      {
        localFileWriter.write("<tr>");
        localFileWriter.write("<td align='center'>" + i + "</td>");
        localFileWriter.write("<td align='center'><a href='" + testCaseReportsFolder + "/" + (String)testCaseFileList.get(m) + "'>" + (String)testCaseNameList.get(m) + "</a></td>");
        localFileWriter.write("<td align='center'>" + (String)testCaseDescriptionList.get(m) + "</td>");
        localFileWriter.write("<td align='center'>" + (String)testCaseStartTimeList.get(m) + "</td>");
        localFileWriter.write("<td align='center'>" + (String)testCaseEndTimeList.get(m) + "</td>");
        localFileWriter.write("<td align='center'>" + (String)testCaseElapsedTimeList.get(m) + "</td>");
        if ((statusMap.get(testCaseNameList.get(m)) != null) && (((String)statusMap.get(testCaseNameList.get(m))).trim().equalsIgnoreCase("Pass")))
        {
          localFileWriter.write("<td align='center'><font color='green'>Pass</font></td>");
          k++;
        }
        else if ((statusMap.get(testCaseNameList.get(m)) != null) && (((String)statusMap.get(testCaseNameList.get(m))).trim().equalsIgnoreCase("Fail")))
        {
          localFileWriter.write("<td align='center'><font color='red'>Fail</font></td>");
          j++;
        }
        else
        {
          localFileWriter.write("<td align='center'><font color='black'>--</font></td>");
        }
        localFileWriter.write("</tr>");
        i++;
      }
      localFileWriter.write("</table>");
      localFileWriter.write("<br/>");
      localFileWriter.write("<h4>Test Cases Summary : </h4>");
      localFileWriter.write("<h4>Total Count: " + (i - 1) + "</h4>");
      localFileWriter.write("<h4><font color='green'>Passed Count</font>: " + k + "</h4>");
      localFileWriter.write("<h4><font color='red'>Failed Count</font>: " + j + "</h4>");
      localFileWriter.write("</body>");
      localFileWriter.write("</head>");
      localFileWriter.write("</html>");
    }
    catch (IOException localIOException3)
    {
      localIOException3.printStackTrace();
    }
    finally
    {
      testCaseNameList.clear();
      testCaseDescriptionList.clear();
      testCaseStartTimeList.clear();
      testCaseEndTimeList.clear();
      testCaseElapsedTimeList.clear();
      testCaseStatusList.clear();
      snapShotPathList.clear();
      testCaseFileList.clear();
      statusMap.clear();
      i = 0;
      k = 0;
      j = 0;
      try
      {
        localFileWriter.flush();
        localFileWriter.close();
      }
      catch (IOException localIOException4)
      {
        localIOException4.printStackTrace();
      }
    }
  }

  public static void generateHTMLReportForTestCases(String paramString1, String paramString2)
  {
    TestName = paramString1;
    FileWriter localFileWriter = null;
    String str = paramString1 + "_" + DATE_FORMAT_NOW.format(new Date()) + ".html";
    File localFile = new File(testFolder + "//" + str);
    testCaseFileList.add(str);
    testCaseNameList.add(paramString1);
    testCaseDescriptionList.add(paramString2);
    int i = 1;
    int j = 0;
    int k = 0;
    int m = 0;
    try
    {
      localFileWriter = new FileWriter(localFile, true);
      localFileWriter.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\"> ");
      localFileWriter.write("<html>");
      localFileWriter.write("<head>");
      localFileWriter.write("<body bgcolor='#E6E6FA'>");
      localFileWriter.write("<div style='background-color:#FFFF00'>");
      localFileWriter.write("<h3>Operating System : " + System.getProperty("os.name") + "</h3>");
      localFileWriter.write("<h3>Test Case Name : " + paramString1 + "</h3>");
      localFileWriter.write("<h3>Test Case Description : " + paramString2 + "</h3>");
      localFileWriter.write("<h3>Date : " + sdf1.format(new Date()) + "</h3>");
      localFileWriter.write("</div>");
      localFileWriter.write("<table border='1' width='100%'>");
      localFileWriter.write("<tr style='background-color:#FF8000'>");
      localFileWriter.write("<th>Step No.</th>");
      localFileWriter.write("<th>Step Description</th>");
      localFileWriter.write("<th>Status</th>");
      localFileWriter.write("<th>Test Step Elapsed Time<br/>(mm:ss)</th>");
      localFileWriter.write("<th>Snapshot</th>");
      localFileWriter.write("</tr>");
      for (int n = 0; n < stepList.size(); n++)
      {
        localFileWriter.write("<tr>");
        localFileWriter.write("<td align='left'>" + i + "</td>");
        localFileWriter.write("<td align='left'>" + (String)stepList.get(n) + "</td>");
        if (((String)stepStatusList.get(n)).trim().equalsIgnoreCase("pass"))
        {
          localFileWriter.write("<td align='center'><font color='green'>" + (String)stepStatusList.get(n) + "</font></td>");
          localFileWriter.write("<td align='center'>" + (String)testStepElapsedTimeList.get(n) + "</td>");
          localFileWriter.write("<td align='center'>--</td>");
          k++;
        }
        else if (((String)stepStatusList.get(n)).trim().equalsIgnoreCase("fail"))
        {
          localFileWriter.write("<td align='center'><font color='red'>" + (String)stepStatusList.get(n) + "</font></td>");
          localFileWriter.write("<td align='center'>" + (String)testStepElapsedTimeList.get(n) + "</td>");
          if ((!snapShotPathList.isEmpty()) && (m < snapShotPathList.size() - 1))
          {
            if ((snapShotPathList.get(m) != null) && (!((String)snapShotPathList.get(m)).trim().equalsIgnoreCase("")))
            {
              localFileWriter.write("<td align='left'><a href='../" + snapShotsFolder + "/" + (String)snapShotPathList.get(m) + "'/>Snapshot</td>");
              m++;
            }
            else
            {
              localFileWriter.write("<td align='left'>--</td>");
            }
          }
          else
            localFileWriter.write("<td align='left'>--</td>");
          j++;
        }
        i++;
        localFileWriter.write("</tr>");
      }
      localFileWriter.write("</table>");
      localFileWriter.write("<br/>");
      localFileWriter.write("<h4>Steps Summary : </h4>");
      localFileWriter.write("<h4>Total Count: " + (i - 1) + "</h4>");
      localFileWriter.write("<h4><font color='green'>Passed Count</font>: " + k + "</h4>");
      localFileWriter.write("<h4><font color='red'>Failed Count</font>: " + j + "</h4>");
      localFileWriter.write("<br/>");
      localFileWriter.write("<table align='center' border='0'>");
      localFileWriter.write("<tr>");
      localFileWriter.write("<td width=100% align='right'>");
      localFileWriter.write("<a href='../" + suiteFilePath + "'><font color= 'black' size='2'><b>Go BACK</b></font></a>");
      localFileWriter.write("</td>");
      localFileWriter.write("</tr>");
      localFileWriter.write("</table>");
      localFileWriter.write("</body>");
      localFileWriter.write("</head>");
      localFileWriter.write("</html>");
      if (j > 0)
        statusMap.put(paramString1, "Fail");
      else
        statusMap.put(paramString1, "Pass");
    }
    catch (IOException localIOException3)
    {
      localIOException3.printStackTrace();
    }
    finally
    {
      try
      {
        localFileWriter.flush();
        localFileWriter.close();
      }
      catch (IOException localIOException4)
      {
        localIOException4.printStackTrace();
      }
      stepStatusList.clear();
      stepList.clear();
      snapShotPathList.clear();
      testStepElapsedTimeList.clear();
      i = 0;
      k = 0;
      j = 0;
      m = 0;
    }
  }

  public static void addStepResults(String paramString1, String paramString2)
  {
    stepList.add(paramString1);
    stepStatusList.add(paramString2);
    long l = System.currentTimeMillis() - testStepTempTime;
    int i = 0;
    int j = (int)(l / 1000L);
    String str = String.valueOf(j);
    if (j >= 60)
    {
      i = j / 60;
      j %= 60;
      str = String.valueOf(j);
    }
    if (j <= 9)
      str = "0" + String.valueOf(j);
    testStepElapsedTimeList.add(i + ":" + str);
    testStepTempTime = System.currentTimeMillis();
  }

  public static void captureSnapShot(WebDriver paramWebDriver)
  {
    try
    {
      File localFile = (File)((TakesScreenshot)paramWebDriver).getScreenshotAs(OutputType.FILE);
      String str1 = "SnapShot_" + DATE_FORMAT_NOW.format(new Date()) + ".png";
      String str2 = snapShotFolder + "/" + str1;
      FileUtils.copyFile(localFile, new File(str2));
      snapShotPathList.add(str1);
      str1 = null;
      str2 = null;
      localFile = null;
    }
    catch (IOException localIOException)
    {
      localIOException.printStackTrace();
    }
  }

  public static void generateSuiteResultFolder()
  {
    File localFile = new File(System.getProperty("user.dir") + "/" + "TestReports");
    if (!localFile.exists())
      localFile.mkdir();
    suiteFolder = new File(localFile + "/" + suiteName + "_" + DATE_FORMAT_NOW.format(new Date()));
    if (!suiteFolder.exists())
      suiteFolder.mkdir();
    testFolder = new File(suiteFolder + "/" + testCaseReportsFolder);
    snapShotFolder = new File(suiteFolder + "/" + snapShotsFolder);
    DataFolder = new File(suiteFolder + "/" + testDataFolder);
    if (!testFolder.exists())
      testFolder.mkdir();
    if (!snapShotFolder.exists())
      snapShotFolder.mkdir();
    if (!DataFolder.exists())
      DataFolder.mkdir();
    suiteFile = new File(suiteFolder + "/" + suiteName + ".html");
    suiteFilePath = suiteName + ".html";
  }

}