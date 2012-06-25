package at.ac.tuwien.RAWverna.ui.serviceprovider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.Icon;

import net.sf.taverna.t2.servicedescriptions.ServiceDescription;
import net.sf.taverna.t2.workflowmodel.processor.activity.Activity;
import at.ac.tuwien.RAWverna.JavaImageEvaluatorActivity;
import at.ac.tuwien.RAWverna.JavaImageEvaluatorActivityConfigurationBean;

public class JavaImageEvaluatorServiceDesc extends ServiceDescription<JavaImageEvaluatorActivityConfigurationBean> {

	private String serviceName;

	/**
	 * The subclass of Activity which should be instantiated when adding a
	 * service for this description
	 */
	@Override
	public Class<? extends Activity<JavaImageEvaluatorActivityConfigurationBean>> getActivityClass() {
		return JavaImageEvaluatorActivity.class;
	}

	/**
	 * The configuration bean which is to be used for configuring the
	 * instantiated activity. Making this bean will typically require some of
	 * the fields set on this service description, like an endpoint URL or
	 * method name.
	 * 
	 */
	@Override
	public JavaImageEvaluatorActivityConfigurationBean getActivityConfiguration() {
		JavaImageEvaluatorActivityConfigurationBean bean = new JavaImageEvaluatorActivityConfigurationBean();
		List<String> measurementURIs = new ArrayList<String>(1);
		measurementURIs.add("outcome://object/image/similarity#equal");
		bean.setMeasurementURIs(measurementURIs);
		return bean;
	}

	/**
	 * An icon to represent this service description in the service palette.
	 */
	@Override
	public Icon getIcon() {
		return RAWvernaServiceIcon.getIcon();
	}

	/**
	 * The display name that will be shown in service palette and will be used
	 * as a template for processor name when added to workflow.
	 */
	@Override
	public String getName() {
		return serviceName;
	}

	/**
	 * The path to this service description in the service palette. Folders will
	 * be created for each element of the returned path.
	 */
	@Override
	public List<String> getPath() {
		// For deeper paths you may return several strings
		return Arrays.asList("RAWverna");
	}

	/**
	 * Return a list of data values uniquely identifying this service
	 * description (to avoid duplicates). Include only primary key like fields,
	 * ie. ignore descriptions, icons, etc.
	 */
	@Override
	protected List<? extends Object> getIdentifyingData() {
		return Arrays.<Object> asList(serviceName);
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

}
