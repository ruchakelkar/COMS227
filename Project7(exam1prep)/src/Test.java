
public class Test 
{
	public Test()
	{
	}

	public boolean foo(int x, int y)
	{
		boolean result = false;
		if (x > y)
		{
			if(y != 0)
			{
				result = true;
			}
		}
		if (x ==0)
		{
			result = true;
		}
		return result;
		}
	}

