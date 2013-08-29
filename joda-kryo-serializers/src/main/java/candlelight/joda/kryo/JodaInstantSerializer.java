package candlelight.joda.kryo;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import org.joda.time.Instant;

/**
 * Joda time Instant serializer
 */
public class JodaInstantSerializer extends Serializer<Instant> {

    @Override
    public void write(Kryo kryo, Output output, Instant t) {
        output.writeLong(t.getMillis(), true);
    }

    @Override
    public Instant read(Kryo kryo, Input input, Class<Instant> type) {
        return new Instant(input.readLong(true));
    }
}
