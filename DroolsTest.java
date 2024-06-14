package com.sample;

import java.util.*;
import org.kie.api.KieBase;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.io.ResourceFactory;

@SuppressWarnings("restriction")
public class DroolsTest {
	static String owner_id = "";
	static String submission_number = "";
	static int choice = 0;
	public static int user_choice(){
    	return choice;
    }
    public static String owner(){
    	return owner_id;
    }
    public static String submission(){
    	return submission_number;
    }
    public static String owner_agency(){
    	String value = owner_id+"/"+submission_number;
    	return value;
    }
    public static void main(String[] args) {
        DroolsTest droolsTest = new DroolsTest();
        droolsTest.run();
    }
    public void run() {
    	KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        kbuilder.add(ResourceFactory.newClassPathResource("process\\sample.bpmn"), ResourceType.BPMN2);
        kbuilder.add(ResourceFactory.newClassPathResource("rules\\Sample.drl"), ResourceType.DRL);
        KieBase kbase = kbuilder.newKnowledgeBase();
        KieSession kSession = kbase.newKieSession();
        	Scanner s = new Scanner(System.in);
        	System.out.println("Get Agency Details using \nowner_id (1)\nsubmission_number (2)\nBoth owner_id and submission_number (3)");
        	choice = s.nextInt();
        	if(choice==1){
        		System.out.println("Enter your owner_id : ");
        		owner_id = s.next();
        	}
        	else if(choice==2){
        		System.out.println("Enter your agency_no : ");
        		submission_number = s.next();
        	}
        	else if(choice==3){
        		System.out.println("Enter your owner_id : ");
        		owner_id = s.next();
        		System.out.println("Enter your submission_number : ");
        		submission_number = s.next();
        	}

        	else{
        		System.out.println("Invalid option");
        	}
        	kSession.startProcess("com.sample.bpmn.hello");
        	kSession.dispose();
    }

}