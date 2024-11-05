# LinkUp Cheatsheet:
## Java `LocalDateTime`

The `LocalDateTime` class in Java allows you to work with date and time without timezone info.

## Basic Usage

### Import `LocalDateTime`

```java
import java.time.LocalDateTime;
```

### 1. Current Date and Time

```java
LocalDateTime now = LocalDateTime.now();
System.out.println("Current DateTime: " + now);
```

### 2. Set Specific Date and Time

Use `of` to specify date and time (Year, Month, Day, Hour, Minute, Second).

```java
LocalDateTime dateTime = LocalDateTime.of(2023, 11, 4, 15, 30, 45);
System.out.println("Set DateTime: " + dateTime);
```

### 3. Modify Existing Date and Time

Use `with` to change specific parts of an existing date and time.

```java
LocalDateTime modified = LocalDateTime.now()
    .withYear(2023).withMonth(11).withDayOfMonth(4)
    .withHour(15).withMinute(30).withSecond(45);
```

- **Add or Subtract Time**:
  ```java
  LocalDateTime tomorrow = dateTime.plusDays(1);  // Adds 1 day
  LocalDateTime nextMonth = dateTime.plusMonths(1);  // Adds 1 month
  LocalDateTime nextYear = dateTime.plusYears(1);  // Adds 1 year
  LocalDateTime inFiveHours = dateTime.plusHours(5);  // Adds 5 hours

  LocalDateTime lastWeek = dateTime.minusWeeks(1);  // Subtracts 1 week
  LocalDateTime tenMinutesAgo = dateTime.minusMinutes(10);  // Subtracts 10 minutes
  ```

For more, see the [official documentation](https://docs.oracle.com/javase/8/docs/api/java/time/LocalDateTime.html).