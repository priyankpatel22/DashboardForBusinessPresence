package priyank.hive.sentimentAnalysis;

import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDFArgumentException;
import org.apache.hadoop.hive.ql.exec.UDFArgumentLengthException;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDF;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.StructObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.DoubleObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;
import org.hamcrest.core.IsInstanceOf;

@Description(name = "Identify Tweet Loation", value = "_FUNC_(Array[]) - find the location of the tweets using coordinates")
public class identifyLocation extends GenericUDF {

	ObjectInspector[] argumentOI;
	
	@Override
	public ObjectInspector initialize(ObjectInspector[] arguments)
			throws UDFArgumentException {
		this.argumentOI = arguments;
		if(arguments == null || arguments.length != 2) {
			throw new UDFArgumentLengthException("No coordinates available");
		}

		ObjectInspector xCoordinate = arguments[0];
		ObjectInspector yCoordinate = arguments[1];
		if(!(xCoordinate instanceof DoubleObjectInspector) || !(xCoordinate instanceof DoubleObjectInspector)) {
			throw new UDFArgumentException("Coordinates are not in form of struct.");
		}
		
		return PrimitiveObjectInspectorFactory.javaShortObjectInspector;
	}
	
	@Override
	public Object evaluate(DeferredObject[] arguments) throws HiveException {
		double xCoordinate = (Double) ((DoubleObjectInspector) argumentOI[0]).getPrimitiveJavaObject(arguments[0].get());
		double yCoordinate = (Double) ((DoubleObjectInspector) argumentOI[1]).getPrimitiveJavaObject(arguments[1].get());
		
		return null;
	}

	@Override
	public String getDisplayString(String[] arg0) {
		// TODO Auto-generated method stub
		return null;
	}
}
