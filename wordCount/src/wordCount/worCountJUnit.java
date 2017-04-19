package wordCount;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.junit.Test;

public class worCountJUnit extends wordCount {

	@Test
	public void test_empty_map()
	{
		Map<String, Integer> tree_map = new TreeMap<String, Integer>();
		
		try
		{
			List<Map.Entry<String, Integer>> list = sort_map( tree_map );
			assertNotNull( list );
			assertTrue( list.size() == 0 );
		}
		catch( Throwable e)
		{
			fail("No exception expected");
		}
	}

	@Test
	public void test_sort_alpha_order()
	{
		Map<String, Integer> tree_map = new TreeMap<String, Integer>();
		String expected_name = "1Name";
		
		tree_map.put( "Name1", 0 );
		tree_map.put( expected_name, 0 );
		
		try
		{
			List<Map.Entry<String, Integer>> list = sort_map( tree_map );
			assertNotNull( list );
			assertEquals( 2, list.size() );
			assertTrue( list.get( 0 ).getKey().equals( expected_name ) );
		}
		catch( Throwable e)
		{
			fail("No exception expected");
		}
	}

	@Test
	public void test_map_order()
	{
		Map<String, Integer> tree_map = new TreeMap<String, Integer>();
		String expected_name = "1Name";
		int expected_value = 5;
		
		tree_map.put( "Name1", 0 );
		tree_map.put( expected_name, 0 );
		tree_map.put( expected_name, expected_value );
		
		try
		{
			List<Map.Entry<String, Integer>> list = sort_map( tree_map );
			assertNotNull( list );
			assertEquals( 2, list.size() );
			assertTrue( list.get( 0 ).getKey().equals( expected_name ) );
			assertEquals( expected_value, list.get( 0 ).getValue().intValue() );
		}
		catch( Throwable e)
		{
			fail("No exception expected");
		}
	}
}
