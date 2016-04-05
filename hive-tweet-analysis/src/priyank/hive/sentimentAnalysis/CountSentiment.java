package priyank.hive.sentimentAnalysis;

import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDFArgumentException;
import org.apache.hadoop.hive.ql.exec.UDFArgumentLengthException;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDF;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.StringObjectInspector;
import org.apache.hadoop.io.Text;

import uk.ac.wlv.sentistrength.SentiStrength;

@Description(name = "Count Sentiment", value = "_FUNC_(String) - Count Sentiment value of the String.")
public class CountSentiment extends GenericUDF{

	ObjectInspector[] argumentOI;
	
	@Override
	public ObjectInspector initialize(ObjectInspector[] arguments)
			throws UDFArgumentException {
		this.argumentOI = arguments;
		if(arguments == null)
		{
			throw new UDFArgumentLengthException("No tweet text available.");
		}
		ObjectInspector tweet = arguments[0];
		if(!(tweet instanceof StringObjectInspector)) {
			throw new UDFArgumentException("tweet is not in form of text.");
		}
		return PrimitiveObjectInspectorFactory.javaStringObjectInspector;
	}

	@Override
	public Text evaluate(DeferredObject[] arguments) throws HiveException {
		String tweet = (String) ((StringObjectInspector) argumentOI[0]).getPrimitiveJavaObject(arguments[0].get()); 
		SentiStrength sentiStrength = new SentiStrength();
		String ssthInitialisation[] = {"sentidata", "/home/hduser/ionidea/sentiStrength/SentStrength_Data/"};
		sentiStrength.initialise(ssthInitialisation);
		
		return new Text(sentiStrength.computeSentimentScores(tweet));
	}
	
	@Override
	public String getDisplayString(String[] arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
