package ex05.pyrmont.core;

import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Set;

import javax.management.ObjectName;
import javax.naming.directory.DirContext;
import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletSecurityElement;
import javax.servlet.descriptor.JspConfigDescriptor;

import org.apache.catalina.AccessLog;
import org.apache.catalina.Authenticator;
import org.apache.catalina.Cluster;
import org.apache.catalina.Container;
import org.apache.catalina.ContainerListener;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.LifecycleListener;
import org.apache.catalina.LifecycleState;
import org.apache.catalina.Loader;
import org.apache.catalina.Manager;
import org.apache.catalina.Pipeline;
import org.apache.catalina.Realm;
import org.apache.catalina.Valve;
import org.apache.catalina.Wrapper;
import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.Response;
import org.apache.catalina.core.ApplicationServletRegistration;
import org.apache.catalina.deploy.ApplicationParameter;
import org.apache.catalina.deploy.ErrorPage;
import org.apache.catalina.deploy.FilterDef;
import org.apache.catalina.deploy.FilterMap;
import org.apache.catalina.deploy.LoginConfig;
import org.apache.catalina.deploy.NamingResources;
import org.apache.catalina.deploy.SecurityConstraint;
import org.apache.catalina.util.CharsetMapper;
//import org.apache.juli.logging.Log;
import org.apache.tomcat.JarScanner;
import org.apache.tomcat.util.http.mapper.Mapper;

public class SimpleContext implements Context, Pipeline{

	protected HashMap children = new HashMap();
	protected Loader loader = null;
	protected 
	@Override
	public void addChild(Container arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addContainerListener(ContainerListener arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addPropertyChangeListener(PropertyChangeListener arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void backgroundProcess() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Container findChild(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Container[] findChildren() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ContainerListener[] findContainerListeners() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void fireContainerEvent(String arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public AccessLog getAccessLog() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getBackgroundProcessorDelay() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Cluster getCluster() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Loader getLoader() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Log getLogger() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Manager getManager() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getMappingObject() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ObjectName getObjectName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Container getParent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ClassLoader getParentClassLoader() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pipeline getPipeline() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Realm getRealm() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DirContext getResources() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void invoke(Request arg0, Response arg1) throws IOException,
			ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void logAccess(Request arg0, Response arg1, long arg2, boolean arg3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeChild(Container arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeContainerListener(ContainerListener arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removePropertyChangeListener(PropertyChangeListener arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setBackgroundProcessorDelay(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setCluster(Cluster arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setLoader(Loader arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setManager(Manager arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setName(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setParent(Container arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setParentClassLoader(ClassLoader arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setRealm(Realm arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setResources(DirContext arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addLifecycleListener(LifecycleListener arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destroy() throws LifecycleException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public LifecycleListener[] findLifecycleListeners() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LifecycleState getState() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getStateName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void init() throws LifecycleException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeLifecycleListener(LifecycleListener arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void start() throws LifecycleException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stop() throws LifecycleException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addValve(Valve arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Valve getBasic() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Container getContainer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Valve getFirst() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Valve[] getValves() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAsyncSupported() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void removeValve(Valve arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setBasic(Valve arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setContainer(Container arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addApplicationListener(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addApplicationParameter(ApplicationParameter arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addConstraint(SecurityConstraint arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addErrorPage(ErrorPage arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addFilterDef(FilterDef arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addFilterMap(FilterMap arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addFilterMapBefore(FilterMap arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addInstanceListener(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addLocaleEncodingMappingParameter(String arg0, String arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addMimeMapping(String arg0, String arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addParameter(String arg0, String arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addResourceJarUrl(URL arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addRoleMapping(String arg0, String arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addSecurityRole(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addServletContainerInitializer(
			ServletContainerInitializer arg0, Set<Class<?>> arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addServletMapping(String arg0, String arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addServletMapping(String arg0, String arg1, boolean arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Set<String> addServletSecurity(ApplicationServletRegistration arg0,
			ServletSecurityElement arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addWatchedResource(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addWelcomeFile(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addWrapperLifecycle(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addWrapperListener(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Wrapper createWrapper() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] findApplicationListeners() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApplicationParameter[] findApplicationParameters() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SecurityConstraint[] findConstraints() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ErrorPage findErrorPage(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ErrorPage findErrorPage(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ErrorPage[] findErrorPages() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FilterDef findFilterDef(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FilterDef[] findFilterDefs() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FilterMap[] findFilterMaps() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] findInstanceListeners() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String findMimeMapping(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] findMimeMappings() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String findParameter(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] findParameters() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String findRoleMapping(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean findSecurityRole(String arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String[] findSecurityRoles() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String findServletMapping(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] findServletMappings() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String findStatusPage(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] findStatusPages() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] findWatchedResources() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean findWelcomeFile(String arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String[] findWelcomeFiles() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] findWrapperLifecycles() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] findWrapperListeners() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean fireRequestDestroyEvent(ServletRequest arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean fireRequestInitEvent(ServletRequest arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getAllowCasualMultipartParsing() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getAltDDName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] getApplicationEventListeners() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] getApplicationLifecycleListeners() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Authenticator getAuthenticator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean getAvailable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getBaseName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CharsetMapper getCharsetMapper() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public URL getConfigFile() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean getConfigured() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getCookies() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getCrossContext() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getDisplayName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean getDistributable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getDocBase() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getEffectiveMajorVersion() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getEffectiveMinorVersion() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getEncodedPath() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean getFireRequestListenersOnForwards() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getIgnoreAnnotations() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public JarScanner getJarScanner() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JspConfigDescriptor getJspConfigDescriptor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean getLogEffectiveWebXml() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public LoginConfig getLoginConfig() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mapper getMapper() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NamingResources getNamingResources() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean getOverride() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getPath() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean getPaused() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getPreemptiveAuthentication() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getPrivileged() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getPublicId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRealPath(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean getReloadable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getResourceOnlyServlets() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServletContext getServletContext() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSessionCookieDomain() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSessionCookieName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSessionCookiePath() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean getSessionCookiePathUsesTrailingSlash() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getSessionTimeout() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean getSwallowAbortedUploads() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getSwallowOutput() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getTldNamespaceAware() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getTldValidation() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getUseHttpOnly() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getWebappVersion() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getWrapperClass() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean getXmlNamespaceAware() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getXmlValidation() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isResourceOnlyServlet(String arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isServlet22() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void reload() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeApplicationListener(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeApplicationParameter(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeConstraint(SecurityConstraint arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeErrorPage(ErrorPage arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeFilterDef(FilterDef arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeFilterMap(FilterMap arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeInstanceListener(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeMimeMapping(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeParameter(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeRoleMapping(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeSecurityRole(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeServletMapping(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeWatchedResource(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeWelcomeFile(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeWrapperLifecycle(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeWrapperListener(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setAllowCasualMultipartParsing(boolean arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setAltDDName(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setApplicationEventListeners(Object[] arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setApplicationLifecycleListeners(Object[] arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setCharsetMapper(CharsetMapper arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setConfigFile(URL arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setConfigured(boolean arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setCookies(boolean arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setCrossContext(boolean arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setDisplayName(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setDistributable(boolean arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setDocBase(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setEffectiveMajorVersion(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setEffectiveMinorVersion(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setFireRequestListenersOnForwards(boolean arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setIgnoreAnnotations(boolean arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setJarScanner(JarScanner arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setLogEffectiveWebXml(boolean arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setLoginConfig(LoginConfig arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setNamingResources(NamingResources arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setOverride(boolean arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPath(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPreemptiveAuthentication(boolean arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPrivileged(boolean arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPublicId(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setReloadable(boolean arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setResourceOnlyServlets(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setSessionCookieDomain(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setSessionCookieName(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setSessionCookiePath(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setSessionCookiePathUsesTrailingSlash(boolean arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setSessionTimeout(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setSwallowAbortedUploads(boolean arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setSwallowOutput(boolean arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setTldNamespaceAware(boolean arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setTldValidation(boolean arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setUseHttpOnly(boolean arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setWebappVersion(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setWrapperClass(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setXmlNamespaceAware(boolean arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setXmlValidation(boolean arg0) {
		// TODO Auto-generated method stub
		
	}

}
