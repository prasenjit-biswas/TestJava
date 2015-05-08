package utility;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class DrawLines extends Applet implements MouseListener,MouseMotionListener, ActionListener{
   
	private final String FIRSTCOORD = "FirstCoord";
	private final String SECONDCOORD = "SecondCoord";
	private final String THIRDCOORD = "ThirdCoord";
	private final String FORTHCOORD = "ForthCoord";
	private final String FIFTHCOORD = "FifthCoord";
	private final String SIXTHCOORD = "SixthCoord";
	private final String SEVENTHCOORD = "SeventhCoord";
	private final String EIGHTHCOORD = "EighthCoord";
	private final String NINTHCOORD = "NinthCoord";
	private int xFrom ;
	private int yFrom ;
    private int xTo  ;
    private int yTo ;
    private List<String> arrList = new ArrayList<String>();
    private Map<String, String> userMap = new HashMap<String, String>();
    private boolean validateFlag = false; 
	
    public void paint(Graphics g){
	  g.drawLine(150,0,150,400);
	  g.drawLine(0,150,400,150);
	  g.drawLine(250,0,250,400);
	  g.drawLine(0,250,400,250);
      setBackground(Color.BLACK);
      setForeground(Color.cyan);
	  setSize(400, 400);
	  
	  if(validateFlag){
		  g.drawString("THE GAME IS OVER", 150, 200);
	  }
	  
	  if(!arrList.isEmpty()){
		  Iterator<String> msgStrItr= arrList.iterator();
		  while(msgStrItr.hasNext()){
			  String msgStr = msgStrItr.next();
			  if(FIRSTCOORD.equals(msgStr)){
				  //X >0 && X<=150 && Y >0 && Y <=150
				  String value = userMap.get(FIRSTCOORD);
				  if("circle".equals(value)){
					  g.drawOval(50, 50, 50, 50); 
				  }else if("cross".equals(value)){
					  g.drawLine(50,50,100,100);
					  g.drawLine(100,50,50,100); 
				  }
			  }
			  if(SECONDCOORD.equals(msgStr)){
				  //X >150 && X<=250 && Y >0 && Y <=150
				  String value = userMap.get(SECONDCOORD);
				  if("circle".equals(value)){
					  g.drawOval(180, 50, 50, 50); 
				  }else if("cross".equals(value)){
					  g.drawLine(180,50,230,100);
					  g.drawLine(230,50,180,100); 
				  }
			  }
			  if(THIRDCOORD.equals(msgStr)){
				  //X >250 && X<=400 && Y >0 && Y <=150
				  String value = userMap.get(THIRDCOORD);
				  if("circle".equals(value)){
					  g.drawOval(300, 50, 50, 50); 
				  }else if("cross".equals(value)){
					  g.drawLine(280,50,330,100);
					  g.drawLine(330,50,280,100); 
				  }
			  }
			  if(FORTHCOORD.equals(msgStr)){
				  //X >0 && X<=150 && Y >150 && Y <=250
				  String value = userMap.get(FORTHCOORD);
				  if("circle".equals(value)){
					  g.drawOval(50, 180, 50, 50); 
				  }else if("cross".equals(value)){
					  g.drawLine(50,180,100,230);
					  g.drawLine(100,180,50,230); 
				  }
			  }
			  if(FIFTHCOORD.equals(msgStr)){
				  //X >150 && X<=250 && Y >150 && Y <=250
				  String value = userMap.get(FIFTHCOORD);
				  if("circle".equals(value)){
					  g.drawOval(180, 180, 50, 50);
				  }else if("cross".equals(value)){
					  g.drawLine(180,180,230,230);
					  g.drawLine(230,180,180,230); 
				  }
			  }
			  if(SIXTHCOORD.equals(msgStr)){
				  //X >250 && X<=400 && Y >150 && Y <=250
				  String value = userMap.get(SIXTHCOORD);
				  if("circle".equals(value)){
					  g.drawOval(300, 180, 50, 50);
				  }else if("cross".equals(value)){
					  g.drawLine(280,180,330,230);
					  g.drawLine(330,180,280,230); 
				  }
			  }
			  if(SEVENTHCOORD.equals(msgStr)){
				  //X >0 && X<=150 && Y >250 && Y <=400
				  String value = userMap.get(SEVENTHCOORD);
				  if("circle".equals(value)){
					  g.drawOval(50, 300, 50, 50);
				  }else if("cross".equals(value)){
					  g.drawLine(50,300,100,350);
					  g.drawLine(100,300,50,350); 
				  }
			  }
			  if(EIGHTHCOORD.equals(msgStr)){
				  //X >150 && X<=250 && Y >250 && Y <=400
				  String value = userMap.get(EIGHTHCOORD);
				  if("circle".equals(value)){
					  g.drawOval(180, 300, 50, 50);
				  }else if("cross".equals(value)){
					  g.drawLine(180,300,230,350);
					  g.drawLine(230,300,180,350); 
				  }
			  }
			  if(NINTHCOORD.equals(msgStr)){
				  //X >250 && X<=400 && Y >250 && Y <=400
				  String value = userMap.get(NINTHCOORD);
				  if("circle".equals(value)){
					  g.drawOval(300, 300, 50, 50);
				  }else if("cross".equals(value)){
					  g.drawLine(280,300,330,350);
					  g.drawLine(330,300,280,350); 
				  }
			  }
		  }
		  //check firstRow
		  evaluate(g,FIRSTCOORD, SECONDCOORD, THIRDCOORD, "firstRow" );
		  //check secondRow
		  evaluate(g,FORTHCOORD, FIFTHCOORD, SIXTHCOORD, "secondRow" );
		  //check thirdRow
		  evaluate(g,SEVENTHCOORD, EIGHTHCOORD, NINTHCOORD, "thirdRow" );
		  //check firstCoulmn
		  evaluate(g,FIRSTCOORD, FORTHCOORD, SEVENTHCOORD,"firstCoulmn" );
		  //check secondCoulmn
		  evaluate(g,SECONDCOORD, FIFTHCOORD, EIGHTHCOORD,"secondCoulmn" );
		  //check thirdCoulmn
		  evaluate(g,THIRDCOORD, SIXTHCOORD, NINTHCOORD,"thirdCoulmn" );
		  //check leftDiagonal
		  evaluate(g,FIRSTCOORD, FIFTHCOORD, NINTHCOORD,"leftDiagonal" );
		  //check rightDiagonal
		  evaluate(g,SEVENTHCOORD, FIFTHCOORD, THIRDCOORD,"rightDiagonal" );
	  }
     
	}
    
    public boolean evaluate(Graphics g, String coord1st, String coord2nd, String coord3rd, String drawLineCoord){
    	if(userMap != null && !userMap.isEmpty()){
    		String firstValue = "";
    		String secondValue = "";
    		String thirdValue = "";
    		if(userMap.get(coord1st)!= null){
    			firstValue = userMap.get(coord1st);
    		}
    		if(userMap.get(coord2nd)!= null){
    			secondValue = userMap.get(coord2nd);
    		}
    		if(userMap.get(coord3rd)!= null){
    			thirdValue = userMap.get(coord3rd);
    		}
    	   if(!("").equals(firstValue) && !("").equals(secondValue) && !("").equals(thirdValue)&&   
    			   firstValue.equals(secondValue) && firstValue.equals(thirdValue) ){
    		   if("firstRow".equals(drawLineCoord)){
    			   g.drawLine(0, 75, 400, 75);
    			   validateFlag = true;
    		   }
    		   if("secondRow".equals(drawLineCoord)){
    			   g.drawLine(0, 205, 400, 205);
    			   validateFlag = true;
    		   }
    		   if("thirdRow".equals(drawLineCoord)){
    			   g.drawLine(0, 325, 400, 325);
    			   validateFlag = true;
    		   }
    		   if("firstCoulmn".equals(drawLineCoord)){
    			   g.drawLine(75, 0, 75, 400);
    			   validateFlag = true;
    		   }
    		   if("secondCoulmn".equals(drawLineCoord)){
    			   g.drawLine(205, 0, 205, 400);
    			   validateFlag = true;
    		   }
    		   if("thirdCoulmn".equals(drawLineCoord)){
    			   g.drawLine(305, 0, 305, 400);
    			   validateFlag = true;
    		   }
    		   if("leftDiagonal".equals(drawLineCoord)){
    			   g.drawLine(0, 0, 400, 400);
    			   validateFlag = true;
    		   }
    		   if("rightDiagonal".equals(drawLineCoord)){
    			   g.drawLine(400, 0, 0, 400);
    			   validateFlag = true;
    		   }
    	   }	
    	}
      return validateFlag;
    }
    
    int X=0,Y=0;
	String msg="MouseEvents";
	
	public void init()
	{
		
		addMouseListener(this);
		addMouseMotionListener(this);
		
		//setBackground(Color.black);
		//setForeground(Color.red);
	}
	
	
	public void mouseEntered(MouseEvent m)
	{
		
		/*setBackground(Color.magenta);
		showStatus("Mouse Entered");
		repaint();*/
	}
	public void mouseExited(MouseEvent m)
	{
		/*setBackground(Color.black);
		showStatus("Mouse Exited");
		repaint();*/
	}
	public void mousePressed(MouseEvent m)
	{
		/*X=10;
		Y=20;
		msg="NEC";
		setBackground(Color.green);
		repaint();*/
	}
	public void mouseReleased(MouseEvent m)
	{
		/*X=10;
		Y=20;
		msg="Engineering";
		setBackground(Color.blue);
		repaint();*/
	}
	public void mouseMoved(MouseEvent m)
	{
		if(validateFlag){
			repaint();
		}
		/*X=m.getX();
		Y=m.getY();
		msg="College";
		setBackground(Color.white);
		showStatus("Mouse Moved");
		repaint();*/
	}
	public void actionPerformed(ActionEvent ev)
    {
		
      
    }

	public void mouseDragged(MouseEvent m)
	{
		/*msg="CSE";
		setBackground(Color.yellow);
		showStatus("Mouse Moved"+m.getX()+" "+m.getY());
		repaint();*/
	}
	public void mouseClicked(MouseEvent m)
	{
		X=m.getX();
		Y=m.getY();
		if(!validateFlag){
		if(X >0 && X<=150 && Y >0 && Y <=150){
			if(!arrList.contains(FIRSTCOORD)){
				if(!userMap.isEmpty()){
					if(!arrList.isEmpty()){
						int index = arrList.size()-1;
						String key = arrList.get(index);
						String value =userMap.get(key);
						if(value != null && !("").equals(value)){
							if("circle".equals(value)){
								userMap.put(FIRSTCOORD, "cross");
							}else{
								userMap.put(FIRSTCOORD, "circle");	
							}
						}
					}
				}else{
					userMap.put(FIRSTCOORD, "circle");
				}
				arrList.add(FIRSTCOORD) ;	
			}
		}
		if(X >150 && X<=250 && Y >0 && Y <=150){
			if(!arrList.contains(SECONDCOORD)){
				if(!userMap.isEmpty()){
					if(!arrList.isEmpty()){
						int index = arrList.size()-1;
						String key = arrList.get(index);
						String value =userMap.get(key);
						if(value != null && !("").equals(value)){
							if("circle".equals(value)){
								userMap.put(SECONDCOORD, "cross");
							}else{
								userMap.put(SECONDCOORD, "circle");	
							}
						}
					}
				}else{
					userMap.put(SECONDCOORD, "circle");
				}
				arrList.add(SECONDCOORD) ;
			}
		}
		if(X >250 && X<=400 && Y >0 && Y <=150){
			if(!arrList.contains(THIRDCOORD)){
				if(!userMap.isEmpty()){
					if(!arrList.isEmpty()){
						int index = arrList.size()-1;
						String key = arrList.get(index);
						String value =userMap.get(key);
						if(value != null && !("").equals(value)){
							if("circle".equals(value)){
								userMap.put(THIRDCOORD, "cross");
							}else{
								userMap.put(THIRDCOORD, "circle");	
							}
						}
					}
				}else{
					userMap.put(THIRDCOORD, "circle");
				}
				arrList.add(THIRDCOORD) ;
			}
		}
		if(X >0 && X<=150 && Y >150 && Y <=250){
			if(!arrList.contains(FORTHCOORD)){
				if(!userMap.isEmpty()){
					if(!arrList.isEmpty()){
						int index = arrList.size()-1;
						String key = arrList.get(index);
						String value =userMap.get(key);
						if(value != null && !("").equals(value)){
							if("circle".equals(value)){
								userMap.put(FORTHCOORD, "cross");
							}else{
								userMap.put(FORTHCOORD, "circle");	
							}
						}
					}
				}else{
					userMap.put(FORTHCOORD, "circle");
				}
				arrList.add(FORTHCOORD) ;
			}
		}
		if(X >150 && X<=250 && Y >150 && Y <=250){
			if(!arrList.contains(FIFTHCOORD)){
				if(!userMap.isEmpty()){
					if(!arrList.isEmpty()){
						int index = arrList.size()-1;
						String key = arrList.get(index);
						String value =userMap.get(key);
						if(value != null && !("").equals(value)){
							if("circle".equals(value)){
								userMap.put(FIFTHCOORD, "cross");
							}else{
								userMap.put(FIFTHCOORD, "circle");	
							}
						}
					}
				}else{
					userMap.put(FIFTHCOORD, "circle");
				}
				arrList.add(FIFTHCOORD) ;
			}
		}
		if(X >250 && X<=400 && Y >150 && Y <=250){
			if(!arrList.contains(SIXTHCOORD)){
				if(!userMap.isEmpty()){
					if(!arrList.isEmpty()){
						int index = arrList.size()-1;
						String key = arrList.get(index);
						String value =userMap.get(key);
						if(value != null && !("").equals(value)){
							if("circle".equals(value)){
								userMap.put(SIXTHCOORD, "cross");
							}else{
								userMap.put(SIXTHCOORD, "circle");	
							}
						}
					}
				}else{
					userMap.put(SIXTHCOORD, "circle");
				}
				arrList.add(SIXTHCOORD) ;
			}
		}
		if(X >0 && X<=150 && Y >250 && Y <=400){
			if(!arrList.contains(SEVENTHCOORD)){
				if(!userMap.isEmpty()){
					if(!arrList.isEmpty()){
						int index = arrList.size()-1;
						String key = arrList.get(index);
						String value =userMap.get(key);
						if(value != null && !("").equals(value)){
							if("circle".equals(value)){
								userMap.put(SEVENTHCOORD, "cross");
							}else{
								userMap.put(SEVENTHCOORD, "circle");	
							}
						}
					}
				}else{
					userMap.put(SEVENTHCOORD, "circle");
				}
				arrList.add(SEVENTHCOORD) ;
			}
		}
		if(X >150 && X<=250 && Y >250 && Y <=400){
			if(!arrList.contains(EIGHTHCOORD)){
				if(!userMap.isEmpty()){
					if(!arrList.isEmpty()){
						int index = arrList.size()-1;
						String key = arrList.get(index);
						String value =userMap.get(key);
						if(value != null && !("").equals(value)){
							if("circle".equals(value)){
								userMap.put(EIGHTHCOORD, "cross");
							}else{
								userMap.put(EIGHTHCOORD, "circle");	
							}
						}
					}
				}else{
					userMap.put(EIGHTHCOORD, "circle");
				}
				arrList.add(EIGHTHCOORD) ;
			}
		}
		if(X >250 && X<=400 && Y >250 && Y <=400){
			if(!arrList.contains(NINTHCOORD)){
				if(!userMap.isEmpty()){
					if(!arrList.isEmpty()){
						int index = arrList.size()-1;
						String key = arrList.get(index);
						String value =userMap.get(key);
						if(value != null && !("").equals(value)){
							if("circle".equals(value)){
								userMap.put(NINTHCOORD, "cross");
							}else{
								userMap.put(NINTHCOORD, "circle");	
							}
						}
					}
				}else{
					userMap.put(NINTHCOORD, "circle");
				}
				arrList.add(NINTHCOORD) ;
			}
		}
	  }
		showStatus("Mouse Clicked "+X+" "+Y+" "+msg);
		//setBackground(Color.white);
		//g.drawOval(10,10,50,100);
		repaint();
	}
	  
	public static void main(){
	
		Graphics g =null;
	 	DrawLines dl = new DrawLines();
	 	dl.paint(g);
	 	System.out.println(dl.msg);
	}
}
