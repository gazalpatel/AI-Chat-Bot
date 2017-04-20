import javax.swing.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.lang.Math;

public class PredictText extends JFrame implements KeyListener
{
	JPanel p = new JPanel();

	JTextArea dialog = new JTextArea(20,50);
	JTextArea input = new JTextArea(1,50);

	//Chat 
	
	String chatBot[][] = {
			//Greeting
			{"hi", "hello", "hola", "helo", "ola", "oy", "ey", "hey"},
			{"Hello there! Biggol is 24/7 online for you","Hello dear!", "Yes, say"},
			//question 1: 
			{"how are you doing", "how r u doing", "hw r u doin", "how are you", "hw are you", "how r you", "how are u", "hw r you", "hw are u","how r u", "hw r u"},
			{"Good","Fine","Amazing","Great","Well! Everything's good!"},
			//question 2
			{"what are you doing", "wt are you doing", "what r you doing", "what are u doing", "what r u doing", "wt r u doing", "wt r u doin"},
			{"Dude! Coing is life", "Sleeping like a panda!", "Having my caffiene, You want some?", "Learning new tech like a nerd", "Being Geeky", "Answering you, blind fella!","Being witty to cut your cum inside"},
			//Negation 1
			{"yes", "ya", "yeah" , "yea", "ha","ya ya", "okay", "ok", "okey", "oki", "yay", "yo", "yaay"},
			{"No", "Never", "No, You can't", "Na", "Nay", "No No", "Ok Fine", "K", "Hmm", "Hm"},
			//Negation 1
			{"no", "never", "no, you can't", "na", "nay", "no no", "ok fine", "k", "hmm", "hm"},
			{"Yes", "Ya", "Yeah" , "Of course yes", "Yea", "Ha","Ya Ya", "Okay", "Ok", "Okey", "Oki", "Yay", "Yo", "Yaay"},
			//default
			{"you need sleep", "idk say else","stupid answer","la la la la" ,"Shut up nood!", "Bullshit!"}
			
	};
	
	JScrollPane scroll = new JScrollPane(
		dialog,
		JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
		JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
	);
	
	public static void main(String[] args)
	{
		new PredictText();
	}
	
	public PredictText()
	{
		super("Predicting Chat Bot");
		setSize(600,400);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		dialog.setEditable(false);
		
		input.addKeyListener(this);
		
		p.add(scroll);
		p.add(input);
		p.setBackground(new Color(255,200,0));
		add(p);
		
		setVisible(true);
		
		//input.setText(Integer.valueOf('a').toString());
	}

	public void keyPressed(KeyEvent arg0) 
	{
		// TODO Auto-generated method stub
		if(arg0.getKeyCode() == KeyEvent.VK_ENTER)
		{
			input.setEditable(false);
			
			//-----grab quote----------
			String quote = input.getText();
			input.setText("");
			addText("\n--> You:\t "+quote);
			quote = quote.trim();
			
			while(
					quote.charAt(quote.length()-1) == '!' ||
					quote.charAt(quote.length()-1) == '.' ||
					quote.charAt(quote.length()-1) == '?'					
				)
			{
				quote = quote.substring(0,quote.length()-1);
			}
			
			quote = quote.trim();
			
			byte response =0;
			/*
			0: searching
			1: did not find
			2: found
			3: found inside
			4: found something similar
			*/
			
			//-----check for matches---
			int j = 0; //Group in ChatBot String array that we are checking
			while(response == 0)
			{
				/*
				if(inArray(quote.toLowerCase(),chatBot[j*2]))
				{
						response = 2;
						
						int r = (int) Math.floor(Math.random() * chatBot[(j*2) + 1].length);
						addText("\n--> Biggol:\t " + chatBot[ (j*2) + 1 ][r] );
				}
				
				if(response != 2)
				{
					if(insideArray(quote,chatBot[j*2]))
					{
						response = 3;
						
						int r = (int) Math.floor(Math.random() * chatBot[(j*2) + 1].length);
						addText("\n--> Biggol:\t Hmm... - " + chatBot[ (j*2) + 1 ][r] );
					}
				}
				*/
				//check for similarity
				//if(response != 2 && response!=3)
				//{
					if(similarInArray(quote,chatBot[j*2]))
					{
						response = 4;
						
						int r = (int) Math.floor(Math.random() * chatBot[(j*2) + 1].length);
						addText("\n--> Biggol:\t If you mean... - "+ chatBot[j*2][r] + "..then.." + chatBot[ (j*2) + 1 ][r] );
					}
					else
					{
						response = 0;
					}
				//}
				
				if(response != 4)
				{
					if(insideArray(quote,chatBot[j*2]))
					{
						response = 3;
						
						int r = (int) Math.floor(Math.random() * chatBot[(j*2) + 1].length);
						addText("\n--> Biggol:\t Hmm... - " + chatBot[ (j*2) + 1 ][r] );
					}
				}
				j++;
				
				//if( j*2 == chatBot.length-1 && response != 2 && response!=3 && response!=4)
				if( j*2 == chatBot.length-1 && response!=4 && response!=3 )
				{
					response = 1;	
				}
				
				//addText("\n");
			}
			
			//-----default-------------
			if( response==1 )
			{
				int r = (int) Math.floor(Math.random() * chatBot[chatBot.length-1].length);
				addText("\n--> Biggol:\t " + chatBot[ chatBot.length-1 ][r] );
			}
		}
	}

	public void keyReleased(KeyEvent arg0) 
	{
		// TODO Auto-generated method stub
		if(arg0.getKeyCode() == KeyEvent.VK_ENTER)
		{
			input.setEditable(true);	
		}
	}

	public void keyTyped(KeyEvent arg0) 
	{
		// TODO Auto-generated method stub
		
	}

	public void addText(String str)
	{
		dialog.setText(dialog.getText()+str);
	}

	public boolean inArray(String in, String[] str)
	{
		boolean match = false;
		//length of array is contant so it is stored in variable length unlike for String object which is done using methof length()
		for(int i=0; i < str.length; i++)
		{
				if(str[i].equals(in))
				{
					match = true;
				}
		}
		
		return match;
	}
	
	public boolean insideArray(String in, String[] str)
	{
		boolean match = false;
		String inL = in.toLowerCase();
		String strL;
		
		//length of array is contant so it is stored in variable length unlike for String object which is done using methof length()
		for(int i=0; i < str.length; i++)
		{
			strL = str[i].toLowerCase();
				if(strL.contains(inL) || inL.contains(strL))
				{
					match = true;
				}
		}
		
		return match;
	}

	public boolean similarInArray(String in, String[] str)
	{
		boolean match = false;
		String inL = in.toLowerCase();
		String strL;
		
		for(int i=0; i < str.length; i++)
		{
			strL = str[i].toLowerCase();
			if(similarity(strL,inL) > 60)
			{
				match = true;
			}
			else
			{
				continue;
			}
		}
		
		return match;
	}
	
	public double similarity(String str1, String str2)
	{
		int i,c = 0;
		Double percentageSimilarity ;
		int len_min,len_max, len1 = str1.length();
		int len2 = str2.length();
		
		if(len1>len2)
		{
			len_min = len2;
			len_max = len1;
		}
		else
		{
			len_min = len1;
			len_max = len2;
		}
		
		/*
		boolean[] simFlag = new boolean[len_max];
		
		for(i=0;i<len_max;i++)
		{
			simFlag = false;
		}
		*/
		
		for(i=0;i<len_min;i++)
		{
			if(str1.charAt(i) == str2.charAt(i))
			{
			//	simFlag[i] = true;
				c++;
			}
			else
			{
			//	simFlag[i] = false;
			}
		}
		
		percentageSimilarity = (c*100.00)/len_max;
		
		//input.setText(str1+"--||||--"+str2+"--||||--"+"sim: "+percentageSimilarity.toString());
		return percentageSimilarity;
	}
}