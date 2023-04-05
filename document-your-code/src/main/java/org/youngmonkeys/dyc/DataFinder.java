package org.youngmonkeys.dyc;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Predicate;

public class DataFinder<T> {

    /**
     * Hàm tìm kiếm phần tử trong một tập hợp và trả về phần tử đầu tiên thỏa mãn điều kiện.
     * Nếu không tìm thấy phần sẽ trả về Option.empty(). Bạn có thể sử dụng như sau:
     * <pre>
     * dataFinder.findFirst(
     *     Arrays.asList(1, 2, 3),
     *         it -> it.equals(2)
     *     )
     *     .orElseThrow(() -> new NoSuchElementException("not found"));
     * </pre>
     *
     * @param collection Tập hợp chứa các phần từ, các phần tử này có thể null.
     * @param condition Điều kiện để lọc ra phần tử cần tìm kếm.
     * @return Phần tử đầu tiên được tìm thấy, thỏa mãn điều kiện.
     */
    public Optional<Object> findFirst(
        Collection<T> collection,
        Predicate<T> condition
    ) {
        for (T item : collection) {
            if (item != null && condition.test(item)) {
                return Optional.of(item);
            }
        }
        return Optional.empty();
    }

    public static void main(String[] args) {
        System.out.println(
            new DataFinder<Integer>()
                .findFirst(
                    Arrays.asList(1, 2, 3),
                    it -> it.equals(2)
                )
        );
        new DataFinder<Integer>()
            .findFirst(
                Arrays.asList(1, 2, 3),
                it -> it.equals(2)
            )
            .orElseThrow(() -> new NoSuchElementException("not found"));
    }
}