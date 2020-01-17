/********************************************************	
 * File:			CBubbleSort.java					*
 * Description:		Java class for running two versions	*
 *					of bubble sort: with flag and		* 
 *					without flag						*
 * Author:			Eric Torman (etorman@hotmail.com)	*
 * Date created:	July 20, 1999 (ET)					*
 * ******************************************************
 */

// Include utility header file (contains Java's Vector class)

class CBubbleSort extends CSortAlgorithm 
	// Flag for determining whether we are running
	// normal bubble sort (w/o flag) or bubble sort
	// with flag

	private Vector code_wo_flag = new Vector();
	private Vector code_w_flag  = new Vector();
		
	* Function:		CBubbleSort() -- Constructor		*
	* Description:	Simple constructor function.		*
	*				Initializes variables of this and	*
	*				derived (CSortAlgorithm) classes.	*
	*													*
	* Input:		Sorting applet object				*
	*				(SortApplet parent)					*
	* Output:		NONE								*
	* Return value:	NONE								*
	* Side effects:	Note that in Java there is no		*
	*				destructor. You can override		*
	*				protected void finalize() function,	*
	*				and it'll get called by Java's		*
	*				garbage	collector					*	
	* ***************************************************
	*/
	public CBubbleSort(SortApplet parent)
	{
		// Set the main applet object
	}
	* Function:		RunBubbleSort_w_Flag()				*
	* Description:	Runs bubble sort w/flag algorithm	*
	*													*
	* Input:		Array of numbers to sort			*
	*				(int arr[])							*
	* Output:		NONE								*
	* Return value:	NONE								*
	* Side effects:	The unsorted array will be replaced	*
	*				by sorted array						*
	* ***************************************************
	*/
	private void RunBubbleSort_w_Flag(int a[])
		SelectLine(0);
		// Actual bubble sort wo/flag code
		for (int i = a.length; --i >= 0; ) 
			// Actual bubble sort wo/flag code		
			boolean	swapped = false;
		
			// Actual bubble sort wo/flag code		
			for (int j = 0; j < i; j++) 
				// Select line of debug window
												
				// Compare a[j] with a[j + 1] -- part of bubble sort
				// w/flag algorithm.
				// NOTE: In addition to comparing two numbers,
				// Compare() function also animates comparision 
				// process (if necessary), updates number of 
				// comparisions and updates the text box with
				// new number of comparisions (if necessary)
				if (Compare(a, j, j + 1) == FIRST_IS_LARGER)

					// Select line of debug window
						
					// Swap a[j] with a[j + 1] -- part of bubble sort
					// w/flag algorithm.
					// NOTE: In addition to swapping two numbers,
					// Swap() function also animates swapping 
					// process (if necessary), updates number of 
					// exchanges and updates the text box with
					// new number of exchanges (if necessary)
					Swap(a, j, j + 1, true);
					// Select of debug window
					swapped = true;
			}
			
			if (m_showAnimation)
				m_sortAnimator.ChangeColor(i, i, m_sortAnimator.SORTED_COLOR); 
			// Select line of debug window
		
			// Actual bubble sort wo/flag code		
			if (!swapped)
				// Cells a[0] to a[i] are already sorted
				// So, change the color of these cells to sorted color
					m_sortAnimator.ChangeColor(0, i, m_sortAnimator.SORTED_COLOR); 
			}
	}
	/****************************************************	
	* Function:		RunBubbleSort_wo_Flag()				*
	* Description:	Runs bubble sort wo/flag algorithm	*
	*													*
	* Input:		Array of numbers to sort			*
	*				(int arr[])							*
	* Output:		NONE								*
	* Return value:	NONE								*
	* Side effects:	The unsorted array will be replaced	*
	*				by sorted array						*
	* ***************************************************
	*/
	private void RunBubbleSort_wo_Flag(int a[])
		SelectLine(0);
		// Actual bubble sort wo/flag code
			// Select line of debug window
			for (int j = 0; j < i; j++) 
				// Select line of debug window
				
				// Compare a[j] with a[j + 1] -- part of bubble sort
				// wo/flag algorithm.
				// NOTE: In addition to comparing two numbers,
				// Compare() function also animates comparision 
				// process (if necessary), updates number of 
				// comparisions and updates the text box with
				// new number of comparisions (if necessary)

				if (Compare(a, j, j + 1) == FIRST_IS_LARGER)
		
					// Select line of debug window
					
					// wo/flag algorithm.
					// NOTE: In addition to swapping two numbers,
					// Swap() function also animates swapping 
					// process (if necessary), updates number of 
					// exchanges and updates the text box with
					// new number of exchanges (if necessary)
					Swap(a, j, j + 1, true);						
				}
			if (m_showAnimation)
				m_sortAnimator.ChangeColor(i, i, m_sortAnimator.SORTED_COLOR); 
	/****************************************************	
	* Function:		OnSort()							*
	* Description:	Function responsible for running	*
	*				actual algorithms					*
	* 													*
	* Input:		Array of numbers to sort			*
	*				(int arr[])							*
	* Output:		NONE								*
	* Return value:	NONE								*
	* Side effects:	Calls one of the private functions	*
	*				to sort the array					*
	* ***************************************************
	*/
	protected void OnSort(int a[])
		// If flag is set, run bubble w/flag algorithm
		// Otherwise, run bubble wo/flag algorithm
	}
	/****************************************************	
	* Function:		BuildDebugCode_w_flag()				*
	* Description:	Builds Java's vector object, which	*
	*				will be used for loading bubble		*
	*				w/flag code to debug window			*
	* 													*
	* Input:		NONE								*
	* Output:		NONE								*
	* Return value:	NONE								*
	* Side effects:	NONE								*
	* ***************************************************
	*/
	private void BuildDebugCode_w_flag()
		// Load, line by line, bubble w/flag code to 
		// our Vector (code_w_flag) object
		code_w_flag.addElement("{");
		code_w_flag.addElement("   boolean swapped = false;");
		code_w_flag.addElement("   for (int j = 0; j < i; j++)"); 
		code_w_flag.addElement("      {");
		code_w_flag.addElement("         Swap(a[j], a[j + 1]);");
		code_w_flag.addElement("   }");
		code_w_flag.addElement("   if (!swapped)");
		code_w_flag.addElement("}");
	}
	/****************************************************	
	* Function:		BuildDebugCode_wo_flag()			*
	* Description:	Builds Java's vector object, which	*
	*				will be used for loading bubble		*
	*				wo/flag code to debug window		*
	* 													*
	* Input:		NONE								*
	* Output:		NONE								*
	* Return value:	NONE								*
	* Side effects:	NONE								*
	* ***************************************************
	*/
	private void BuildDebugCode_wo_flag()
		// Load, line by line, bubble wo/flag code to 
		// our Vector (code_wo_flag) object
		code_wo_flag.addElement("{"); 
	
	* Function:		load_wflag_DebugCode()				*
	* Description:	Loads our bubble w/flag code to		*
	*				debug's window list box				*
	* 													*
	* Input:		NONE								*
	* Output:		NONE								*
	* Return value:	NONE								*
	* Side effects:	NONE								*
	* ***************************************************
	*/
	public void load_wflag_DebugCode()
		// Clear debug's window list box
		for (int i = 0; i < code_w_flag.size(); i++)
			m_debugWindow.AddLine((String)(code_w_flag.elementAt(i))); 
	
	/****************************************************	
	* Function:		load_woflag_DebugCode()				*
	* Description:	Loads our bubble wo/flag code to	*
	*				debug's window list box				*
	* 													*
	* Input:		NONE								*
	* Output:		NONE								*
	* Return value:	NONE								*
	* Side effects:	NONE								*
	* ***************************************************
	*/
	public void load_woflag_DebugCode()
		// Add a string to the debug's window list box
		for (int i = 0; i < code_wo_flag.size(); i++)
			m_debugWindow.AddLine((String)(code_wo_flag.elementAt(i))); 
}