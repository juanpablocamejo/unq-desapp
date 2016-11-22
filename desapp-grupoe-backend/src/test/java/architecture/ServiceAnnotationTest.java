package architecture;

import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertTrue;

public class ServiceAnnotationTest {

    private static final String PACKAGE_PATH_SEPARATOR = ".";

    private static final String GETTER_METHOD_NAME_ID = "get";

    private static final String FILE_PATH_SEPARATOR = System.getProperty("file.separator");

    private static final String SERVICE_BASE_PACKAGE_PATH = "\\" + "services" + "\\" + "appservice";

    private static final String SETTER_METHOD_NAME_ID = "set";

    private static final String TEST_CLASS_FILENAME_ID = "Test";

    private List<Class> serviceClasses;

    @Before
    public void findServiceClasses() throws IOException, ClassNotFoundException {
        serviceClasses = new ArrayList<>();
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources("classpath*:" + SERVICE_BASE_PACKAGE_PATH + "/**/*.class");
        for (Resource resource : resources) {
            if (isNotTestClass(resource)) {
                String serviceClassCandidateNameWithPackage = parseClassNameWithPackage(resource);
                ClassLoader classLoader = resolver.getClassLoader();
                Class serviceClassCandidate = classLoader.loadClass(serviceClassCandidateNameWithPackage);
                if (isNotInterface(serviceClassCandidate)) {
                    if (isNotException(serviceClassCandidate)) {
                        if (isNotEnum(serviceClassCandidate)) {
                            if (isNotAnonymousClass(serviceClassCandidate)) {
                                serviceClasses.add(serviceClassCandidate);
                            }
                        }
                    }
                }
            }
        }
    }

    private boolean isNotTestClass(Resource resource) {
        return !resource.getFilename().contains(TEST_CLASS_FILENAME_ID);
    }

    private boolean isNotException(Class exceptionCanditate) {
        return !Exception.class.isAssignableFrom(exceptionCanditate) &&
                !RuntimeException.class.isAssignableFrom(exceptionCanditate) &&
                !Throwable.class.isAssignableFrom(exceptionCanditate);
    }

    private String parseClassNameWithPackage(Resource resource) throws IOException {
        String pathFromClasspathRoot = parsePathFromClassPathRoot(resource.getFile().getAbsolutePath());
        String pathWithoutFilenameSuffix = parsePathWithoutFilenameSuffix(pathFromClasspathRoot);
        return buildClassNameFromPath(pathWithoutFilenameSuffix);
    }

    private String parsePathFromClassPathRoot(String absolutePath) {
        int classpathRootIndex = absolutePath.indexOf(SERVICE_BASE_PACKAGE_PATH);
        return absolutePath.substring(classpathRootIndex + 1);
    }

    private String parsePathWithoutFilenameSuffix(String path) {
        int prefixIndex = path.indexOf(PACKAGE_PATH_SEPARATOR);
        return path.substring(0, prefixIndex);
    }

    private String buildClassNameFromPath(String path) {
        return path.replace(FILE_PATH_SEPARATOR, PACKAGE_PATH_SEPARATOR);
    }

    private boolean isNotInterface(Class interfaceCanditate) {
        return !interfaceCanditate.isInterface();
    }

    private boolean isNotEnum(Class enumCanditate) {
        return !enumCanditate.isEnum();
    }

    private boolean isNotAnonymousClass(Class anonymousClassCanditate) {
        return !anonymousClassCanditate.isAnonymousClass();
    }

    @Test
    public void eachServiceMethodHasTransactionalAnnotation() {
        for (Class serviceClass : serviceClasses) {
            Method[] serviceMethods = serviceClass.getDeclaredMethods();
            for (Method serviceMethod : serviceMethods) {
                if (isNotGetterOrSetterMethod(serviceMethod)) {
                    boolean transactionalAnnotationFound = serviceMethod.isAnnotationPresent(Transactional.class);
                    assertTrue("Method " + serviceMethod.getName() + " of " + serviceClass.getName() + " class must be annotated with @Transactional annotation.", transactionalAnnotationFound);
                }
            }
        }
    }

    private boolean isNotGetterOrSetterMethod(Method method) {
        return !method.getName().contains(SETTER_METHOD_NAME_ID) && !method.getName().contains(GETTER_METHOD_NAME_ID);
    }

}
