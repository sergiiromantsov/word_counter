package wordCount;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.regex.PatternSyntaxException;

public class wordCount
{

	public static void main(String[] args)
	{
		if (args.length != 2)
		{
			usage();
			return;
		}
		String file_path    = args[0];
		int words_to_print = 0;
		try
		{
			words_to_print = Integer.parseInt( args[1] );
		}
		catch ( NumberFormatException e )
		{
			System.out.println( "Failed: " + e.toString() );
		}

		File file = new File( file_path );
		Scanner scanner = null;
		Map<String, Integer> tree_map = new TreeMap<String, Integer>();
		try
		{
			scanner = new Scanner( file ).useDelimiter( _delimiter_l1 );
			while( scanner.hasNext() )
			{
				String word = scanner.next();
				if (!word.isEmpty())
				{
					if (true)
					{
						//System.out.println( sub_words[i] );
						String low_case_word = word.toLowerCase();
						Integer count        = tree_map.get( low_case_word );
						tree_map.put( low_case_word, count != null ? ++count : 1 );
					}
					else
					{
						String[] sub_words = word.split( _delimiter_l2 );
						for (int i = 0; i < sub_words.length; ++i)
						{
							if (!sub_words[i].isEmpty())
							{
								//System.out.println( sub_words[i] );
								String low_case_word = sub_words[i].toLowerCase();
								Integer count = tree_map.get( low_case_word );
								tree_map.put( low_case_word, count != null ? ++count : 1 );
							}
						}
					}
				}
			}
			
			List<Map.Entry<String, Integer>> sorted_data = sort_map( tree_map );
			print_result( sorted_data, words_to_print );
		}
		catch ( FileNotFoundException e )
		{
			System.out.println( "Failed: " + e.toString() );
		}
		catch ( PatternSyntaxException e )
		{
			System.out.println( "Failed: " + e.toString() );
		}
		catch ( Throwable e )
		{
			System.out.println( "Failed: " + e.toString() );
		}
		finally
		{
			if (scanner != null)
				scanner.close();
		}
	}


	private static void usage()
	{
		System.out.println( "Usage: wordCount file_name words_to_out" );
	}


	static List<Map.Entry<String, Integer>> sort_map( Map<String, Integer> map )
	{
		List<Map.Entry<String, Integer>> list =
            new LinkedList<Map.Entry< String, Integer >>( map.entrySet() );

		Collections.sort( list, new Comparator<Map.Entry< String, Integer >>()
			{
				public int compare(Map.Entry<String, Integer> o1,
						Map.Entry<String, Integer> o2)
				{
					int result = (o1.getValue()).compareTo( o2.getValue() );
					//return result > 0 ? -1 : result < 0 ? 1 : 0;
					return result * -1;
				}
			});

		return list;
	}


	private static void print_result( List<Map.Entry<String, Integer>> sorted_data, int count )
	{
		if (count <= 0)
		{
			System.out.println( "Sorry, nothing to print" );
			return;
		}

		Iterator<Entry<String, Integer>> it = sorted_data.iterator();
		while (it.hasNext() && count-- > 0)
		{
			System.out.println( it.next().toString() );
		}
	}


	private final static String _delimiter_l1 = "[^a-zA-Z0-9]+";
	private final static String _delimiter_l2 = "^[-]+|[-]+$|^[']+|[']+$";

}
