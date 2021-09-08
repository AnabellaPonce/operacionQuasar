package com.operacion.fuego.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.operacion.fuego.exceptions.OperacionException;

/**
 * Servicio que representa el manejo de mensajes
 *
 * @author aponce
 */

public class MessageServices {

		public String getMessage(List<List<String>> messages) throws OperacionException{
			List<String> msgComplete = getMsgComplete(messages);
	        if(!validateMessagesSize(messages, msgComplete.size()))
	            throw new OperacionException("Tamaño del mensaje incorrecto");
	      
	        int s = 0;
	        for(int i = 0; i < messages.size(); i++){
	            s = messages.get(i).size();
	            messages.set(i, messages.get(i).subList(s-msgComplete.size(), s));
	        }
	        
	        String message = completeMessage(messages);
	        if(!validateMessageComplete(msgComplete,message))
	            throw new OperacionException("No se puede conocer el mensaje");

	        return message;
		}
		
		public List<String> getMsgComplete(List<List<String>> msgList){

	        List<String> list = new ArrayList<String>();
	        for( List<String> msg : msgList){
	        	list = Stream.concat(list.stream(), msg.stream())
	                    .distinct()
	                    .collect(Collectors.toList());
	        }
	        list.remove("");
	        return list;
	    }
		
		public boolean validateMessagesSize(List<List<String>> messages, int size){
	        for(List<String> m : messages){
	            if(m.size() < size){
	                return false;
	            }
	        }
	        return true;
	    }
		
		public String completeMessage(List<List<String>> msgList){

	        String phrase = "";
	        for(List<String> m : msgList){

	            if(m.size()>0 && !m.get(0).equals("")){
	                phrase = (m.size() == 1) ? m.get(0) : m.get(0) + " ";
	                msgList.stream().forEach( s -> s.remove(0));
	                return  phrase + completeMessage(msgList);
	            }
	        }
	       
	        return "";
	    }
		
		public boolean validateMessageComplete(List<String> msgcomplete, String message){
	        List<String> msg = Arrays.stream(message.split(" ")).collect(Collectors.toList());
	        Collections.sort(msgcomplete);
	        Collections.sort(msg);
	        return Arrays.equals(msgcomplete.toArray(), msg.toArray());
	    }

	}

