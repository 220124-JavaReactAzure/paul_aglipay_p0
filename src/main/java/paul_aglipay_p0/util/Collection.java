package paul_aglipay_p0.util;

public interface Collection<T> {
	boolean add(T element);

	boolean contains(T element);

	boolean isEmpty();

	boolean remove(T element);

	int size();
}
