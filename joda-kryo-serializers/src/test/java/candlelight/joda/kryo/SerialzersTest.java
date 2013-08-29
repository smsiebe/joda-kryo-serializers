package candlelight.joda.kryo;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.Instant;
import org.joda.time.Interval;
import org.joda.time.LocalDate;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

/**
 *
 */
public class SerialzersTest {

    private static JodaTest expected;
    private static JodaTest deserialized;

    @BeforeClass
    public static void before() {
        expected = new JodaTest();

        Kryo kryo = new Kryo();
        kryo.register(DateTime.class, new JodaDateTimeSerializer());
        kryo.register(Duration.class, new JodaDurationSerializer());
        kryo.register(Instant.class, new JodaInstantSerializer());
        kryo.register(Interval.class, new JodaIntervalSerializer());
        kryo.register(LocalDate.class, new JodaLocalDateSerializer());

        ByteArrayOutputStream bout = null;
        Output out = null;
        ByteArrayInputStream bin = null;
        Input in = null;
        try {
            bout = new ByteArrayOutputStream();
            out = new Output(bout);
            kryo.writeObject(out, expected);
            out.flush();
            bin = new ByteArrayInputStream(bout.toByteArray());
            in = new Input(bin);
            deserialized = kryo.readObject(in, JodaTest.class);
        } finally {
            if (bout != null) {
                try {
                    bout.close();
                } catch (IOException ex) {
                }
            }
            if (out != null) {
                out.close();
            }
            if (bin != null) {
                try {
                    bin.close();
                } catch (IOException ex) {
                }
            }
            if (in != null) {
                in.close();
            }
        }
    }

    @Test
    public void testDateTime() {
        assertEquals(expected.dateTime, deserialized.dateTime);
    }

    @Test
    public void testDuration() {
        assertEquals(expected.duration, deserialized.duration);
    }

    @Test
    public void testInstant() {
        assertEquals(expected.instant, deserialized.instant);
    }

    @Test
    public void testInterval() {
        assertEquals(expected.interval, deserialized.interval);
    }

    @Test
    public void testLocalDate() {
        assertEquals(expected.localDate, deserialized.localDate);
    }

    private static class JodaTest {

        private DateTime dateTime = new DateTime();
        private Duration duration = new Duration(10L);
        private Instant instant = new Instant();
        private Interval interval = new Interval(10L, 20L);
        private LocalDate localDate = new LocalDate();
    }
}
