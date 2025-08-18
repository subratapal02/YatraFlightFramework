package base;


import com.aventstack.extentreports.ExtentTest;

public class ExtentFactory {//Declares a singleton class ExtentFactory for managing ExtentTest instances.
	private ExtentFactory() {//Private constructor prevents external instantiation.

	}

	private static ExtentFactory instance = new ExtentFactory();//Creates a single static instance of ExtentFactory.

	public static ExtentFactory getInstance() {//Provides global access to the instance.
		return instance;
	}
	//Uses ThreadLocal to store ExtentTest instances, ensuring separate reports in parallel execution.
	ThreadLocal<ExtentTest> extent = new ThreadLocal<ExtentTest>();

	public ExtentTest getExtent() {//Retrieves the ExtentTest instance.
		return extent.get();
	}

	public void setExtent(ExtentTest extenttestparam) {//Sets the ExtentTest instance
		extent.set(extenttestparam);
	}
	
	public void removeExtentObject() {//Removes the ExtentTest instance.
		extent.remove();
	}

}