package com.biscato.kollaidoscope.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.biscato.kollaidoscope.model.Question;
import com.biscato.kollaidoscope.persistence.QuestionDAO;


@Path("/questionaire")
public class QuestionaireResource {
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public List<Question> getAllQuestionaire() {
		QuestionDAO questionDAO = new QuestionDAO();
 		return questionDAO.getAllEntities();
//		return new ArrayList<Question>(createTestQuestions());	
	}

	@GET
	@Path("{id}")
	public Question getQuestionForId(@PathParam("id") long id) {
		QuestionDAO questionDAO = new QuestionDAO();
		//TODO: Error handling: a) wrong id b) no id (e.g. character instead of int) c) other error
		return questionDAO.getEntityForId(id);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<Question> createQuestion(List<Question> newQuestions)	 {
		QuestionDAO questionDAO = new QuestionDAO();
		//TODO: Error handling when wrong data is supplied or information is missing
		
		ArrayList<Question> list = new ArrayList<Question>();
		for(Question quest : newQuestions){
			list.add(questionDAO.createEntity(quest));
		}
		return list;
	}
	
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	public void deleteAllQuestions(){
		QuestionDAO question = new QuestionDAO();
		question.deleteAllEntities();
	}
//	
//	private List<Question> createTestQuestions(){
//
//		final String german = new String("de"); //TODO: get locale from device
//		//final String english = new String("en");
//		final String categoryVisionDe = new String("Vision");
//		final String categoryInnovationDe = new String("Unterst�tzung von Innvovation");
//		final String categoryTaskorientationDe = new String("Aufgabenorientierung");
//		final String categorySecurityDe = new String("Partizipative Sicherheit");
//		
//		ArrayList<Question> list = new ArrayList<Question>();
//				
//		//1
//		Question newEntry = new Question(german, "In diesem Team ist allen klar, was wir erreichen wollen.", categoryVisionDe);
//		list.add(newEntry);
//		newEntry = null;
//		//2
//		newEntry = new Question(german, "Wir wissen, dass wir uns aufeinander verlassen k�nnen.", categoryInnovationDe);
//		list.add(newEntry);
//		newEntry = null;
//		//3
//		newEntry = new Question(german, "Wir haben anregende Diskussionen dar�ber, wie wir am besten arbeiten.", categoryTaskorientationDe);
//		list.add(newEntry);
//		newEntry = null;
//		//4
//		newEntry = new Question(german, "Wir treffen uns ausreichend h�ufig, um effektiv zu kommunizieren und zu koordinieren.", categorySecurityDe);
//		list.add(newEntry);
//		newEntry = null;
//		//5
//		newEntry = new Question(german, "Die Teammitglieder bieten einander immer schnell Hilfe an, um etwas Neues auszuprobieren.", categoryInnovationDe);
//		list.add(newEntry);
//		newEntry = null;
//		//6
//		newEntry = new Question(german, "Wir haben alle Einfluss auf endg�ltige Entscheidungen im Team.", categorySecurityDe);
//		list.add(newEntry);
//		newEntry = null;
//		//7
//		newEntry = new Question(german, "Wir halten uns �ber arbeitsrelevante Dinge gegenseitig auf dem Laufenden.", categorySecurityDe);
//		list.add(newEntry);
//		newEntry = null;
//		//8
//		newEntry = new Question(german, "In unserem Team herrscht ein Gef�hl von Sicherheit und Vertrauen.", categorySecurityDe);
//		list.add(newEntry);
//		newEntry = null;
//		//9
//		newEntry = new Question(german, "Wir sind jederzeit aufgeschlossen gegen�ber neuen Ideen.", categoryInnovationDe);
//		list.add(newEntry);
//		newEntry = null;
//		//10
//		newEntry = new Question(german, "Alle Teammitglieder f�hlen sich den Zielen des Teams verpflichtet.", categoryVisionDe);
//		list.add(newEntry);
//		newEntry = null;
//		//11
//		newEntry = new Question(german, "Wir k�nnen offen �ber Fehler sprechen.", categoryTaskorientationDe);
//		list.add(newEntry);
//		newEntry = null;
//		//12
//		newEntry = new Question(german, "Wir stimmen mit unsere Ziele �berein.", categoryVisionDe);
//		list.add(newEntry);
//		newEntry = null;
//		//13
//		newEntry = new Question(german, "Es herrscht bei uns eine Atmosph�re, in der konstruktive Kritik ge�bt wird.", categoryTaskorientationDe);
//		list.add(newEntry);
//		newEntry = null;
//		//14
//		newEntry = new Question(german, "Wir unterst�tzen einander in Ideen �ber neue und verbesserte Arbeitsprozesse.", categoryInnovationDe);
//		list.add(newEntry);
//		newEntry = null;
//		//15
//		newEntry = new Question(german, "Wir unterst�tzen uns gegenseitig bei der Erledigung unserer Aufgabe.", categoryTaskorientationDe);
//		list.add(newEntry);
//		newEntry = null;
//
//		return list;
//        
////        
////        Entity question16 = new Entity("Question", questionairKey);
////        question16.setProperty("Language", "de");
////        question16.setProperty("Question ID", "16");
////        question16.setProperty("Question", "Jeder im Team tr�gt zur Entscheidungsfindung bei.");
////        question16.setProperty("Category", "Partizipative Sicherheit");
////        datastore.put(question16);
////        
////        Entity question17 = new Entity("Question", questionairKey);
////        question17.setProperty("Language", "de");
////        question17.setProperty("Question ID", "17");
////        question17.setProperty("Question", "Unsere Teamregeln beinhalten auch Erwartungen zum Kommunikations- und R�ckmeldeverhalten. (Vereinbarungen)");
////        question17.setProperty("Category", "Struktur");
////        datastore.put(question17);
////        
////        Entity question18 = new Entity("Question", questionairKey);
////        question18.setProperty("Language", "de");
////        question18.setProperty("Question ID", "18");
////        question18.setProperty("Question", "Wir haben durchg�ngig die richtigen Werkzeuge f�r effektive Zusammenarbeit verf�gbar. (Infrastruktur)");
////        question18.setProperty("Category", "Struktur");
////        datastore.put(question18);
////        
////        Entity question19 = new Entity("Question", questionairKey);
////        question19.setProperty("Language", "de");
////        question19.setProperty("Question ID", "19");
////        question19.setProperty("Question", "Alle im Team haben ausreichende Kenntnisse f�r die situativ angemessene Anwendung der bereitgestellten Kooperationswerkzeuge. (Medienkompetenz)");
////        question19.setProperty("Category", "Virtuelle Kompetenz");
////        datastore.put(question19);
////        
////        Entity question20 = new Entity("Question", questionairKey);
////        question20.setProperty("Language", "de");
////        question20.setProperty("Question ID", "20");
////        question20.setProperty("Question", "Unsere Teambesprechungen finden regelm��ig statt und werden sorgf�ltig vorbereitet. Inhalte, Ziele und Ablauf sind klar.  (Regeln)");
////        question20.setProperty("Category", "Struktur");
////        datastore.put(question20);
////        
////        Entity question21 = new Entity("Question", questionairKey);
////        question21.setProperty("Language", "de");
////        question21.setProperty("Question ID", "21");
////        question21.setProperty("Question", "Im Team werden die vorhandenen Kommunikationskan�le gem�� der getroffenen Absprachen  effizient eingesetzt.  (Kommunikation)");
////        question21.setProperty("Category", "Virtuelle Kompetenz");
////        datastore.put(question21);
////        
////        Entity question22 = new Entity("Question", questionairKey);
////        question22.setProperty("Language", "de");
////        question22.setProperty("Question ID", "22");
////        question22.setProperty("Question", "In unserem Team besteht eine angemessene Balance zwischen Autonomie /Flexibilit�t und Zusammenhalt. (Selbstorganisation)");
////        question22.setProperty("Category", "Virtuelle Kompetenz");
////        datastore.put(question22);
////        
////        Entity question23 = new Entity("Question", questionairKey);
////        question23.setProperty("Language", "de");
////        question23.setProperty("Question ID", "23");
////        question23.setProperty("Question", "Wir haben eine klare Verteilung von Rollen, Aufgaben und Verantwortlichkeiten im Team. (Klarheit)");
////        question23.setProperty("Category", "Struktur");
////        datastore.put(question23);
////        
////        Entity question24 = new Entity("Question", questionairKey);
////        question24.setProperty("Language", "de");
////        question24.setProperty("Question ID", "24");
////        question24.setProperty("Question", "Es gibt auch regelm��ig informelle Kontakte zwischen allen Teammitgliedern. (Vertrauen)");
////        question24.setProperty("Category", "Virtuelle Kompetenz");
////        datastore.put(question24);
////        
////        Entity question1b = new Entity("Question", questionairKey);
////        question1b.setProperty("Language", "en");
////        question1b.setProperty("Question ID", "1");
////        question1b.setProperty("Question", "We all know our common objectives.");
////        question1b.setProperty("Category", "vision");
////        datastore.put(question1b);
////        
////        Entity question2b = new Entity("Question", questionairKey);
////        question2b.setProperty("Language", "en");
////        question2b.setProperty("Question ID", "2");
////        question2b.setProperty("Question", "We know that we can rely on each other.");
////        question2b.setProperty("Category", "support of innovation");
////        datastore.put(question2b);
////        
////        Entity question3b = new Entity("Question", questionairKey);
////        question3b.setProperty("Language", "en");
////        question3b.setProperty("Question ID", "3");
////        question3b.setProperty("Question", "We have inspiring discussions about how to best cooperate.");
////        question3b.setProperty("Category", "task oriented");
////        datastore.put(question3b);
////        
////        Entity question4b = new Entity("Question", questionairKey);
////        question4b.setProperty("Language", "en");
////        question4b.setProperty("Question ID", "4");
////        question4b.setProperty("Question", "We meet often enough to effectively communicate and cooperate.");
////        question4b.setProperty("Category", "security");
////        datastore.put(question4b);
////        
////        Entity question5b = new Entity("Question", questionairKey);
////        question5b.setProperty("Language", "en");
////        question5b.setProperty("Question ID", "5");
////        question5b.setProperty("Question", "Team members always quickly offer help in order to test new approaches.");
////        question5b.setProperty("Category", "support of innovation");
////        datastore.put(question5b);
////        
////        Entity question6b = new Entity("Question", questionairKey);
////        question6b.setProperty("Language", "en");
////        question6b.setProperty("Question ID", "6");
////        question6b.setProperty("Question", "We all have an influence on final team decisions.");
////        question6b.setProperty("Category", "security");
////        datastore.put(question6b);
////        
////        Entity question7b = new Entity("Question", questionairKey);
////        question7b.setProperty("Language", "en");
////        question7b.setProperty("Question ID", "7");
////        question7b.setProperty("Question", "We constantly keep each other up to date in regard to issues relevant to our work.");
////        question7b.setProperty("Category", "security");
////        datastore.put(question7b);
////        
////        Entity question8b = new Entity("Question", questionairKey);
////        question8b.setProperty("Language", "en");
////        question8b.setProperty("Question ID", "8");
////        question8b.setProperty("Question", "In our team, there is an atmosphere of security and trust.");
////        question8b.setProperty("Category", "security");
////        datastore.put(question8b);
////        
////        Entity question9b = new Entity("Question", questionairKey);
////        question9b.setProperty("Language", "en");
////        question9b.setProperty("Question ID", "9");
////        question9b.setProperty("Question", "We are receptive to new ideas at all times.");
////        question9b.setProperty("Category", "support of innovation");
////        datastore.put(question9b);
////        
////        Entity question10b = new Entity("Question", questionairKey);
////        question10b.setProperty("Language", "en");
////        question10b.setProperty("Question ID", "10");
////        question10b.setProperty("Question", "All team members are comitted to the team's objectives.");
////        question10b.setProperty("Category", "vision");
////        datastore.put(question10b);
////        
////        Entity question11b = new Entity("Question", questionairKey);
////        question11b.setProperty("Language", "en");
////        question11b.setProperty("Question ID", "11");
////        question11b.setProperty("Question", "We can openly adress mistakes.");
////        question11b.setProperty("Category", "task oriented");
////        datastore.put(question11b);
////        
////        Entity question12b = new Entity("Question", questionairKey);
////        question12b.setProperty("Language", "en");
////        question12b.setProperty("Question ID", "12");
////        question12b.setProperty("Question", "We agree on our objectives.");
////        question12b.setProperty("Category", "vision");
////        datastore.put(question12b);
////        
////        Entity question13b = new Entity("Question", questionairKey);
////        question13b.setProperty("Language", "en");
////        question13b.setProperty("Question ID", "13");
////        question13b.setProperty("Question", "The team atmosphere fosters constructive critisism.");
////        question13b.setProperty("Category", "task oriented");
////        datastore.put(question13b);
////        
////        Entity question14b = new Entity("Question", questionairKey);
////        question14b.setProperty("Language", "en");
////        question14b.setProperty("Question ID", "14");
////        question14b.setProperty("Question", "We support each other in developing new and modified processes.");
////        question14b.setProperty("Category", "support of innovation");
////        datastore.put(question14b);
////        
////        Entity question15b = new Entity("Question", questionairKey);
////        question15b.setProperty("Language", "en");
////        question15b.setProperty("Question ID", "15");
////        question15b.setProperty("Question", "We support each other in fullfilling our tasks. ");
////        question15b.setProperty("Category", "task oriented");
////        datastore.put(question15b);
////        
////        Entity question16b = new Entity("Question", questionairKey);
////        question16b.setProperty("Language", "en");
////        question16b.setProperty("Question ID", "16");
////        question16b.setProperty("Question", "Every one in the team contributes to decision making.");
////        question16b.setProperty("Category", "security");
////        datastore.put(question16b);
////        
////        Entity question17b = new Entity("Question", questionairKey);
////        question17b.setProperty("Language", "en");
////        question17b.setProperty("Question ID", "17");
////        question17b.setProperty("Question", "Our team rules include expectations respective... (agreements)");
////        question17b.setProperty("Category", "structure");
////        datastore.put(question17b);
////        
////        Entity question18b = new Entity("Question", questionairKey);
////        question18b.setProperty("Language", "en");
////        question18b.setProperty("Question ID", "18");
////        question18b.setProperty("Question", "We have all the tools we need for working together effectively. (infrastructure)");
////        question18b.setProperty("Category", "structure");
////        datastore.put(question18b);
////        
////        Entity question19b = new Entity("Question", questionairKey);
////        question19b.setProperty("Language", "en");
////        question19b.setProperty("Question ID", "19");
////        question19b.setProperty("Question", "Alle im Team haben ausreichenen Kenntnisse f�r die situativ angemessene Anwendung enr bereitgestellten Kooperationswerkzeuge. (Medienkompetenz)");
////        question19b.setProperty("Category", "virtual competence");
////        datastore.put(question19b);
////        
////        Entity question20b = new Entity("Question", questionairKey);
////        question20b.setProperty("Language", "en");
////        question20b.setProperty("Question ID", "20");
////        question20b.setProperty("Question", "Unsere Teambesprechungen finenn regelm��ig statt und werenn sorgf�ltig vorbereitet. Inhalte, Ziele und Ablauf sind klar.  (Regeln)");
////        question20b.setProperty("Category", "structure");
////        datastore.put(question20b);
////        
////        Entity question21b = new Entity("Question", questionairKey);
////        question21b.setProperty("Language", "en");
////        question21b.setProperty("Question ID", "21");
////        question21b.setProperty("Question", "Im Team werenn die vorhanennen Kommunikationskan�le gem�� enr getroffenen Absprachen  effizient eingesetzt.  (Kommunikation)");
////        question21b.setProperty("Category", "virtual competence");
////        datastore.put(question21b);
////        
////        Entity question22b = new Entity("Question", questionairKey);
////        question22b.setProperty("Language", "en");
////        question22b.setProperty("Question ID", "22");
////        question22b.setProperty("Question", "In unserem Team besteht eine angemessene Balance zwischen Autonomie /Flexibilit�t und Zusammenhalt. (Selbstorganisation)");
////        question22b.setProperty("Category", "virtual competence");
////        datastore.put(question22b);
////        
////        Entity question23b = new Entity("Question", questionairKey);
////        question23b.setProperty("Language", "en");
////        question23b.setProperty("Question ID", "23");
////        question23b.setProperty("Question", "Wir haben eine klare Verteilung von Rollen, Aufgaben und Verantwortlichkeiten im Team. (Klarheit)");
////        question23b.setProperty("Category", "structure");
////        datastore.put(question23b);
////        
////        Entity question24b = new Entity("Question", questionairKey);
////        question24b.setProperty("Language", "en");
////        question24b.setProperty("Question ID", "24");
////        question24b.setProperty("Question", "Es gibt auch regelm��ig informelle Kontakte zwischen allen Teammitglieenrn. (Vertrauen)");
////        question24b.setProperty("Category", "virtual competence");
////        datastore.put(question24b);
//	}
}
