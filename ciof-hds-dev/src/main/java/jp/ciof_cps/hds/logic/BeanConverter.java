/**
 * BeanConverter.java
 * (C) 2019. Industrial Value Chain Initiative
 */
package jp.ciof_cps.hds.logic;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * This class is the utility class for JavaBeans.
 */
public final class BeanConverter {

	static final Log LOG = LogFactory.getLog(BeanConverter.class);

	/** A constructor. */
	private BeanConverter() {
		super();
	}
	

	/**
	 * Copies properties from source instance to destination instance.
	 * @param <SRC> Type of source object.
	 * @param <DST> Type of destination object.
	 * @param src the Source Instance.
	 * @param dst the Destination Instance.
	 * @param dstClass the Class of Destination Instance.
	 * @return the destination instance.
	 */
	public static <SRC, DST> DST copyProperties(SRC src, DST dst, Class<? extends DST> dstClass) {
		if (dst != null && src != null) {
			wrap(()->{
				BeanUtils.copyProperties(dst, src);
				return null;
			});
		}
		return dst;
	}

	/**
	 * Copies properties from source instance to destination instance.
	 * @param <SRC> Type of source object.
	 * @param <DST> Type of destination object.
	 * @param src the Source Instance.
	 * @param dst the Destination Instance.
	 * @param dstClass the Class of Destination Instance.
	 * @param excludeCopyPropNames names of property to be excluded always
	 * @param excludeNullCopyPropNames names of property to be excluded when it is null
	 * @return the destination instance.
	 */
	public static <SRC, DST> DST copyProperties(SRC src, DST dst, Class<? extends DST> dstClass, String[] excludeCopyPropNames, String[] excludeNullCopyPropNames) {
		if (dst != null && src != null) {
			BeanUtilsBean customBeanUtils = new BeanUtilsBean() {
				@Override
				public void copyProperty(Object dest, String name, Object value)
						throws IllegalAccessException, InvocationTargetException {
					if (Arrays.asList(excludeCopyPropNames).contains(name)) {
						return;
					} else if (Arrays.asList(excludeNullCopyPropNames).contains(name) && value == null) {
						return;
					}
					super.copyProperty(dest, name, value);
				}
			};
			
			wrap(()->{
				customBeanUtils.copyProperties(dst, src);
				return null;
			});
		}
		return dst;
	}

	/**
	 * Creates copied instance based on properties of source instance.
	 * @param <SRC> Type of source object.
	 * @param <DST> Type of destination object.
	 * @param src the Source Instance.
	 * @param dstClass the Class of Destination Instance.
	 * @return the destination instance.
	 */
	public static <SRC, DST> DST createCopiedInstance(SRC src, Class<? extends DST> dstClass) {
		DST dst = null;
		if (src != null) {
			dst = wrap(()->{
				DST dstObj = dstClass.getConstructor().newInstance();
				BeanUtils.copyProperties(dstObj, src);
				return dstObj;
			});
		}
		return dst;
	}
	
	/**
	 * Convert source bean instance to destination bean instance.
	 * @param <SRC> Type of source object.
	 * @param <DST> Type of destination object.
	 * @param src the Source Instance.
	 * @param dstClass the Class of Destination Instance.
	 * @return the destination instance.
	 */
	public static <SRC, DST> DST convert(SRC src, Class<? extends DST> dstClass) {
		return createCopiedInstance(src, dstClass);
	}
	
	/**
	 * Convert source bean instance list to destination bean instance list.
	 * @param <SRC> Type of source object.
	 * @param <DST> Type of destination object.
	 * @param srcs the Source Instance List.
	 * @param dstClass the Class of Destination Instance.
	 * @return the destination instance list.
	 */
	public static <SRC, DST> List<DST> convert(List<SRC> srcs, Class<? extends DST> dstClass) {
		List<DST> dsts = new ArrayList<>();
		for (SRC src : srcs) {
			dsts.add(convert(src, dstClass));
		}
		return dsts;
	}
	
	/**
	 * Wrap exceptions related to reflective operations.
	 * @param <RETURN_TYPE> the type of result
	 * @param executor the Executor
	 * @return the result value
	 */
	private static <RETURN_TYPE> RETURN_TYPE wrap(Executor<RETURN_TYPE> executor) {
		RETURN_TYPE obj = null;
		try {
			obj = executor.execute();
		} catch (IllegalArgumentException | ReflectiveOperationException | SecurityException e) {
			String msg = "Unexpected error occurs.";
			LOG.debug(msg, e);
			throw new RuntimeException(msg, e);
		}
		return obj;
	}

	/**
	 * This is the executor interface for reflective operation.
	 * @param <RETURN_TYPE> the type of result
	 */
	private interface Executor<RETURN_TYPE> {
		/**
		 * Execute operations.
		 * @return the result
		 * @throws IllegalArgumentException thrown when Invalid arguments specified.
		 * @throws SecurityException thrown when security violation occurs.
		 * @throws ReflectiveOperationException thrown when reflective operation failed.
		 */
		RETURN_TYPE execute() throws IllegalArgumentException, SecurityException, ReflectiveOperationException;
	}
}
