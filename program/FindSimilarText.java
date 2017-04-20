import javax.swing.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.lang.Math;

public class FindSimilarText extends JFrame implements KeyListener
{
	JPanel p = new JPanel();

	JTextArea dialog = new JTextArea(20,50);
	JTextArea input = new JTextArea(1,50);

	String similarString;
	byte[] simFlag;
	//Chat 

		String[] phraseList = {		"hola","how r u","fine","okay","cool"		};
		
		JScrollPane scroll = new JScrollPane(
		dialog,
		JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
		JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
	);
	
	public static void main(String[] args)
	{
		new FindSimilarText();
	}
	
	public FindSimilarText()
	{
		super("Find Similar Text Bot");
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
			quote = quote.toLowerCase();
			similarString = SearchString(quote);
			addText("\n--> biggol:\t "+similarString);
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

	public String SearchString(String phrase)
	{
		int i,match = 0;
		
		/*
			0: searching
			1: did not find
			2: found
			3: found inside //contains 2: found
			4: found something similar
		*/
		
		/*
		for(i=0; i < phraseList.length; i++)
		{
			if(phrase.equals(phraseList[i]))
			{
				match = 2;
				return phraseList[i];
			}
		}
		*/
		/*
		for(i=0; i < phraseList.length; i++)
		{
			if(phrase.contains(phraseList[i]) || phraseList[i].contains(phrase))
			{
				match = 3;
				return phraseList[i];
			}
		}
		*/
		for(i=0; i < phraseList.length; i++)
		{
			if(similarity(phrase,phraseList[i]) > 70)
			{
				match = 4;
				return phraseList[i];
			}
			else
			{
				continue;
			}
		}
		
		for(i=0; i < phraseList.length; i++)
		{
			if(phrase.contains(phraseList[i]) || phraseList[i].contains(phrase))
			{
				match = 3;
				return phraseList[i];
			}
		}
		return " ";
	}

	public double similarity(String phrase_i, String phrase_l)
	{
		int i,c = 0, len = phrase_i.length();
		Double percentageSimilarity ;
		simFlag = new byte[len];
		
		for(i=0;i<len;i++)
		{
			if(phrase_i.charAt(i) == phrase_l.charAt(i))
			{
				simFlag[i] = 1;
				c++;
			}
			else
			{
				simFlag[i] = 0;
			}
		}
		
		percentageSimilarity = (c*100.00)/len;
		
		return percentageSimilarity;
		
	}
}
	