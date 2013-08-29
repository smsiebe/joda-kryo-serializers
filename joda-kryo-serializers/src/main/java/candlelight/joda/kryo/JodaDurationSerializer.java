package candlelight.joda.kryo;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import org.joda.time.Duration;

/**
 * Serializes the duration into millis
 */
public class JodaDurationSerializer extends Serializer<Duration> {

    @Override
    public void write(Kryo kryo, Output output, Duration t) {
        output.writeLong(t.getMillis(), true);
    }

    @Override
    public Duration read(Kryo kryo, Input input, Class<Duration> type) {
        return new Duration(input.readLong(true));
    }
}
