package fawry.sofAutomation.utils;

import java.util.ArrayList;


public class Utils {
	

	
	/* Translate a 2D List to 2D Object Array. */
	public Object[][] translateListToArray(ArrayList<ArrayList<Object>> dataList)
	{
		Object ret[][] = null;
		if(dataList!=null)
		{
			/* Get row count. */
			int rowSize = dataList.size();
			if(rowSize>0)
			{
				ArrayList<Object> rowList = dataList.get(0);
				
				/* Get column count. */
				int colSize = rowList.size();
				
				if(colSize>0)
				{
					/* Init the 2D array with the row and column count.*/
					ret = new String[rowSize][colSize];
					
					/* Loop for rows. */
					for(int i=0;i<rowSize;i++)
					{
						/* Loop the columns for each row. */
						rowList = dataList.get(i);
						for(int j=0;j<colSize;j++)
						{
							/* Assign column data in one row.*/
							ret[i][j] = rowList.get(j);
						}
					}
				}
			}
		}
		return ret;
	}


}
