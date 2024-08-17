import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private static final int MAX_SIZE = 10_000;

    private final Resume[] storage = new Resume[MAX_SIZE];
    private int size = 0;

    void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    void save(Resume r) {
        if (r == null || indexOf(r.uuid) != -1 || size == MAX_SIZE) {
            return;
        }
        storage[size++] = r;
    }

    Resume get(String uuid) {
        int index = indexOf(uuid);
        return index == -1 ? null : storage[index];
    }

    void delete(String uuid) {
        int index = indexOf(uuid);
        if (index == -1) {
            return;
        }
        if (index != size - 1) {
            storage[index] = storage[size - 1];
        }
        storage[--size] = null;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    int size() {
        return size;
    }

    private int indexOf(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid == null && storage[i].uuid == null || storage[i].uuid != null && storage[i].uuid.equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
