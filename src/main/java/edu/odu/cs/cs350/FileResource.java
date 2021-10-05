package edu.odu.cs.cs350;
/**
 * the FileResource class is used for mainly any 
 * other type  of media
 */
public class FileResource {

	private String name;
	private String localPath;
	private int size;
	private String category;

	/**
	 * Create FileResource object without variables
	 */
	public FileResource()
	{
		this.name="";
		this.localPath="";
		this.size=0;
		this.category="";
	}
	
	/**
	 *  Create FileResource object with variables
	 *  
	 * @param name name of file
	 * @param localPath local path to file
	 * @param size size of file
	 * @param category type of file
	 */
	public FileResource(String name, String localPath, int size, String category)
	{
		this.name = name;
		this.localPath=localPath;
		this.size=size;
		this.category=category;
	}
	
	/**
	 *  returns the name as a String
	 * 
	 * @return file name
	 */
	public String getName()
	{
		return this.name;
	}

	/**
	 *  sets the name variable
	 * 
	 *  @param file name to assign
	 */
	public void setName(String name)
	{
		this.name = name;
	}
	
	/**
	 *  returns the localPath as a String
	 *  
	 *  @return local path as a string
	 */
	public String getLocalPath()
	{
		return this.localPath;
	}

	/**
	 * sets the localPath variable
	 * 
	 * @param local path to assign
	 */
	public void setLocalPath(String localPath)
	{
		this.localPath = localPath;
	}
	
	/**
	 *  returns the size as an int
	 *  
	 *  @return size of file
	 */
	public int getSize()
	{
		return this.size;
	}

	/**
	 *  sets the size variable
	 *  
	 *  @param size size of file to assign
	 */
	public void setSize(int size)
	{
		this.size = size;
	}
	
	/**
	 *  returns the category as a String
	 *  
	 *  @return type of file as a string
	 */
	public String getCategory()
	{
		return this.category;
	}

	/**
	 * sets the category variable
	 * 
	 * @param category type of file to assign
	 */
	public void setCategory(String category)
	{
		this.category = category;
	}

	/**
	 *  make a copy of a FileResource object
	 *  
	 *  @param fileResource object to be copied
	 */
	public void CopyFileResource(FileResource fileResource)
	{
		this.name = fileResource.getName();
		this.localPath = fileResource.getLocalPath();
		this.size = fileResource.getSize();
		this.category = fileResource.getCategory();
	}


}

