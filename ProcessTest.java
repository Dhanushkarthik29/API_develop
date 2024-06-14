package com.sample;

import java.util.*;
import org.kie.api.KieBase;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.io.ResourceFactory;

 
@SuppressWarnings("restriction")
public class ProcessTest {
	static String owner_id = "";
	static String submission_number = "";
	static int option = 0;
	public static int user_choice(){
    	return option;
    }
    public static String owner(){
    	return owner_id;
    }
    public static String submission(){
    	return submission_number;
    }
    public static String owner_submission(){
    	String value = owner_id+"/"+submission_number;
    	return value;
    }
    public static void main(String[] args) throws Exception {
        ProcessTest processTest = new ProcessTest();
        processTest.run();
    }
    public void run() throws Exception {
    	KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        kbuilder.add(ResourceFactory.newClassPathResource("process\\sample.bpmn"), ResourceType.BPMN2);
        kbuilder.add(ResourceFactory.newClassPathResource("rules\\Sample.drl"), ResourceType.DRL);
        KieBase kbase = kbuilder.newKnowledgeBase();
        KieSession kSession = kbase.newKieSession();
        	Scanner ob = new Scanner(System.in);
        	System.out.println("ProcessTest");
        	System.out.println("1.Owner_id");
        	System.out.println("2.Submission_Number");
        	System.out.println("3.Both Owner_id and Submission_Number");
        	System.out.println("-------------------------------------------");
        	System.out.println("Enter Your Choice Here:");
        	option = ob.nextInt();
        	switch(option){
        		case 1:
        			System.out.println("Enter your owner_id : ");
        			owner_id = ob.next();
        			break;
        		
        		case 2:
        			System.out.println("Enter your submission_number : ");
        			submission_number = ob.next();
        			break;
        		case 3:
        			System.out.println("Enter your owner_id : ");
        			owner_id = ob.next();
        			System.out.println("Enter your submission_number : ");
        			submission_number = ob.next();
        			break;
        		default:
        			System.out.println("Invalid option");
        			break;               
        	}
        	ob.close();
        	kSession.startProcess("com.sample.bpmn.hello");
        	kSession.dispose();
    }
}