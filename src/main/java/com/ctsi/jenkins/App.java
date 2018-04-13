package com.ctsi.jenkins;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;

import org.apache.log4j.Logger;

import com.offbytwo.jenkins.JenkinsServer;

/**
 * Hello world!
 *
 */
public class App {
	
	static final Logger log = Logger.getLogger(App.class);
	
	public static void main(String[] args) {
		
		String url = "http://192.168.12.125:30080";
		try {
			JenkinsServer jenkins = new JenkinsServer(new URI(url), "admin", "admin");
			
//			Map<String, Job> map = jenkins.getJobs();
//			log.info(map.size());
			
			String path = "/home/lb/temp/jenkins/config.xml";
			String jobName = "maven_demo";
			String jobXml = getXml(path);
			log.info(jobXml);
			jenkins.createJob(jobName, jobXml);
			
			
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
//		String xml = "<?xml version='1.1' encoding='UTF-8'?><maven2-moduleset plugin=\"maven-plugin@3.1.2\">  <keepDependencies>false</keepDependencies>  <properties/>  <scm class=\"hudson.scm.NullSCM\"/>  <canRoam>false</canRoam>  <disabled>false</disabled>  <blockBuildWhenDownstreamBuilding>false</blockBuildWhenDownstreamBuilding>  <blockBuildWhenUpstreamBuilding>false</blockBuildWhenUpstreamBuilding>  <triggers/>  <concurrentBuild>false</concurrentBuild>  <aggregatorStyleBuild>true</aggregatorStyleBuild>  <incrementalBuild>false</incrementalBuild>  <ignoreUpstremChanges>false</ignoreUpstremChanges>  <ignoreUnsuccessfulUpstreams>false</ignoreUnsuccessfulUpstreams>  <archivingDisabled>false</archivingDisabled>  <siteArchivingDisabled>false</siteArchivingDisabled>  <fingerprintingDisabled>false</fingerprintingDisabled>  <resolveDependencies>false</resolveDependencies>  <processPlugins>false</processPlugins>  <mavenValidationLevel>-1</mavenValidationLevel>  <runHeadless>false</runHeadless>  <disableTriggerDownstreamProjects>false</disableTriggerDownstreamProjects>  <settings class=\"jenkins.mvn.DefaultSettingsProvider\"/>  <globalSettings class=\"jenkins.mvn.DefaultGlobalSettingsProvider\"/>  <reporters/>  <publishers/>  <buildWrappers/>  <prebuilders/>  <postbuilders/></maven2-moduleset>";
		
	}
	
	public static String getXml(String path) {
		
		try (
				FileInputStream fis = new FileInputStream(path);
				BufferedReader br = new BufferedReader(new InputStreamReader(fis));
				) {
			
			StringBuilder sb = new StringBuilder(2048);
			String line = null;
			
			while (null != br && null != (line = br.readLine())) {
				sb.append(line);
			}
			
			return sb.toString();
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		
		return "";
	}
	
}
