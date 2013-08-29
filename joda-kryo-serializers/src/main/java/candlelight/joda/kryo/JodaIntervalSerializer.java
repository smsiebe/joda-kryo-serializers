package candlelight.joda.kryo;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import org.joda.time.Interval;

/**
 *
 */
public class JodaIntervalSerializer extends Serializer<Interval> {

    @Override
    public void write(Kryo kryo, Output output, Interval t) {
        output.writeString(t.toString());
    }

    @Override
    public Interval read(Kryo kryo, Input input, Class<Interval> type) {
        return Interval.parse(input.readString());
    }
}
