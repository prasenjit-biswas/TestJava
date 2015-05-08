package Dataretrieval.Question;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;

public class VectorAdapter extends ArrayList {
	public VectorAdapter(){
		super();
	}
	public VectorAdapter(Collection obj){
		super(obj);
	}
	public Object elementAt(int index){
		return get(index);
	}
	public void addElement(Object obj){
		add(obj);
	}
	public void insertElementAt(Object obj, int index){
		add(index,obj);
	}
	public void setElementAt(Object obj, int index){
		set(index,obj);
	}
	public void removeElementAt(int index){
		remove(index);
	}
	public void removeElement(Object obj){
		remove(obj);
	}
	public Enumeration elements(){
		return Collections.enumeration(this);
	}
}
