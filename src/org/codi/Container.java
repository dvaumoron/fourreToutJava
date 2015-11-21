package org.codi;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Container {

	public static void main(String[] args) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		Container container = new Container();
		container.set(ToWire.class, "setList", Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
		container.link(SubInterface.class, SubObject.class);
		System.out.println(container.get(ToWire.class));
	}
	
	private Map<String, Object> mapObject = new HashMap<String, Object>();
	private Map<Class<?>, Class<?>> mapClass = new HashMap<Class<?>, Class<?>>();

	public Container() {
	}

	public void set(Class<?> clazz, String methodName, Object instance) {
		mapObject.put(clazz.getName() + '.' + methodName, instance);
	}

	public <T> void link(Class<T> parent, Class<? extends T> implementation) {
		mapClass.put(parent, implementation);
	}

	public <T> T get(Class<T> clazz) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {
		Class<T> usedClass = (Class<T>) mapClass.get(clazz);
		if (usedClass == null) {
			usedClass = clazz;
		}
		String name = usedClass.getName();
		T instance = (T) mapObject.get(name);
		if (instance == null) {
			instance = usedClass.newInstance();
			mapObject.put(name, instance);
			autowire(instance);
		}
		return instance;
	}

	public void autowire(Object instance) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {
		Class<?> instanceClass = instance.getClass();
		for (Method method : instanceClass.getMethods()) {
			Class<?>[] parameterTypes = method.getParameterTypes();
			if (parameterTypes.length == 1) {
				String methodName = method.getName();
				Object subInstance = mapObject.get(instanceClass.getName() + '.' + methodName);
				if (subInstance != null) {
					method.invoke(instance, subInstance);
				} else if (methodName.startsWith("set")) {
					subInstance = get(parameterTypes[0]);
					if (subInstance != null) {
						method.invoke(instance, subInstance);
					}
				}
			}
		}
	}

}

class ToWire {

	private List<Integer> list;
	private SubInterface subInterface;
	private ToWire toWire;

	public void setList(List<Integer> list) {
		this.list = list;
	}

	public void setSubObject(SubInterface subInterface) {
		this.subInterface = subInterface;
	}

	public void setToWire(ToWire toWire) {
		this.toWire = toWire;
	}

	@Override
	public String toString() {
		return "ToWire [list=" + list + ", subInterface=" + subInterface + ", toWire=" + (toWire == this) + "]";
	}

}

interface SubInterface {
	
}

class SubObject implements SubInterface {

	@Override
	public String toString() {
		return "SubObject";
	}

}