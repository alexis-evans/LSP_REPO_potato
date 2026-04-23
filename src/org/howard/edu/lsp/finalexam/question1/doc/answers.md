## Part 1:

Shared Resource #1: `nextId` (shared counter across threads)\
Shared Resource #2: `requests` list (shared collection)\
Concurrency problem: Race condition (threads interfere with each other when accessing shared data)\
Why addRequest() is unsafe: 
* Multiple threads can call `getNextId()` at the same time
* This can result in:
    * Duplicate IDs
    * Skipped IDs
* Also, `requests.add()` is not thread-safe → list corruption possible

## Part 2:

Fix A: Explanation
- Not correct
- Synchronizing `getNextId()` only protects ID generation
- The `requests.add()` operation remains unsynchronized, so race conditions still exist

Fix B: Explanation
- Correct
- Synchronizing `addRequest()` ensures only one thread executes the entire method at a time
- This protects both ID generation and list modification
- Prevents race conditions completely

Fix C: Explanation
- Not correct
- Synchronizing `getRequests()` only protects read access
- It does not prevent concurrent modifications in `addRequest()`

## Part 3:
**Answer + Explanation:**
- No, `getNextId()` should not be public

- According to Riel’s heuristics, internal implementation details should be hidden
- ID generation is an internal responsibility of `RequestManager`
- Making it public increases coupling and allows misuse by external classes

## Part 4:

**Description:**
- Use a thread-safe approach without `synchronized`, such as:
  - `AtomicInteger` for safe ID generation
  - A synchronized collection for safe list operations
- This avoids manual locking while ensuring thread safety

**Code Snippet:**
```java
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class RequestManager {
    private AtomicInteger nextId = new AtomicInteger(1);
    private List<String> requests = Collections.synchronizedList(new ArrayList<>());

    public void addRequest(String studentName) {
        int id = nextId.getAndIncrement();
        String request = "Request-" + id + " from " + studentName;
        requests.add(request);
    }
}