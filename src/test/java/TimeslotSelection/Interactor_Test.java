//package TimeslotSelection;
//
//import daos.MongoDAO;
//import entity.*;
//import interface_adapter.ViewManagerModel;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Nested;
//import org.junit.jupiter.api.Test;
//import usecases.timeslot_selection.TimeslotSelectionInteractor;
//import usecases.timeslot_selection.TimeslotSelectionOutputBoundary;
//import views.ViewManager;
//
//import java.time.LocalDateTime;
//import java.time.LocalTime;
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class TimeslotSelectionInteractorTest {
//    private TimeslotSelectionInteractor interactor;
//    private CommonEventFactory eventFactory;
//
//    private final UserFactory userFactory = new CommonUserFactory();
//    private final CalendarFactory calendarFactory = new CommonCalendarFactory();
//    private final MessageFactory messageFactory = new CommonMessageFactory();
//    private final GroupFactory groupFactory = new CommonGroupFactory();
//    private final ViewManagerModel viewManagerModel = new ViewManagerModel();
//    private final MongoDAO mongoDAO = new MongoDAO(groupFactory, messageFactory, calendarFactory, userFactory, eventFactory);
//
//
//    @BeforeEach
//    void setUp() {
//        eventFactory = new CommonEventFactory();
//        // Assuming interactor setup allows passing a factory
//        interactor = new TimeslotSelectionInteractor(mongoDAO, eventFactory);
//        System.out.println("Setup completed with TimeslotSelectionInteractor initialized.");
//    }
//
//    @Nested
//    class TimeslotTests {
//        List<Event> events;
//        LocalDateTime duration;
//
//        @BeforeEach
//        void setup() {
//            events = new ArrayList<>();
//            duration = LocalDateTime.of(2023, 10, 29, 2, 0);  // Duration for the timeslot
//            System.out.println("Test setup for TimeslotTests - Duration set to " + duration);
//        }
//
//        @Test
//        void testSelectTimeslotNoOverlap() {
//            // No events, expecting first slot in the morning
//            LocalDateTime expected = LocalDateTime.now().with(LocalTime.of(9, 0));
//            System.out.println("Running testSelectTimeslotNoOverlap, no overlapping events.");
//            LocalDateTime actual = interactor.selectTimeslot(events);
//
//            // Check if the timeslot is as expected
//            System.out.println("Expected timeslot: " + expected + ", Actual timeslot: " + actual);
//            assertTrue(actual.toLocalTime().equals(expected.toLocalTime()), "Should select 9 AM when there are no events");
//        }
//
//        @Test
//        void testSelectTimeslotWithOverlap() {
//            // Event from 9 AM to 11 AM, expecting next available slot
//            events.add(eventFactory.create("Morning Meeting", LocalDateTime.now().with(LocalTime.of(9, 0)), LocalDateTime.now().with(LocalTime.of(11, 0)), true));
//            System.out.println("Running testSelectTimeslotWithOverlap, existing event from 9 AM to 11 AM.");
//            LocalDateTime expected = LocalDateTime.now().with(LocalTime.of(11, 0));
//            LocalDateTime actual = interactor.selectTimeslot(events);
//
//            // Check if the timeslot is right after the existing event
//            System.out.println("Expected timeslot: " + expected + ", Actual timeslot: " + actual);
//            assertEquals(expected, actual, "Should select 11 AM when earlier slot is taken");
//        }
//
//        @Test
//        void testNoValidTimeslotAvailable() {
//            // Fill the day with events
//            System.out.println("Running testNoValidTimeslotAvailable, filling day with events every 2 hours from 9 AM to 10 PM.");
//            for (int hour = 9; hour < 22; hour += 2) {
//                events.add(eventFactory.create("Block Event", LocalDateTime.now().with(LocalTime.of(hour, 0)), LocalDateTime.now().with(LocalTime.of(hour + 2, 0)), true));
//            }
//            LocalDateTime actual = interactor.selectTimeslot(events);
//
//            // Expect null or a specific flag indicating no slot available
//            System.out.println("No valid timeslot available, test result: " + (actual == null ? "null" : actual.toString()));
//            assertNull(actual, "Should return null when no slot is available");
//        }
//    }
//}
