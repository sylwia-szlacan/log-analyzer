package com.test.loganalyzer;

import com.test.loganalyzer.facade.LogAnalyzerFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;

@SpringBootApplication
public class LogAnalyzerApplication implements CommandLineRunner {

	private static Logger log = LoggerFactory.getLogger(LogAnalyzerApplication.class);

	@Autowired
	private LogAnalyzerFacade facade;

	public static void main(String[] args) {
		if (validInitialFileName(args)) {
			SpringApplication.run(LogAnalyzerApplication.class, args);
		}
	}

	@Override
	public void run(final String... args) throws Exception {
		String fileName = args[0];
		log.info("Starting an application with log file: " + fileName);
		facade.analyzeLogs(fileName);
	}

	private static boolean validInitialFileName(String[] args){
		if (args.length == 0){
			log.error("No required log file name");
			return false;
		}
		String fileName = args[0];
		File logFile = new File(fileName);
		if (!logFile.exists()){
			log.error("There is no file like: " + fileName);
			return false;
		}
		return true;
	}
}
