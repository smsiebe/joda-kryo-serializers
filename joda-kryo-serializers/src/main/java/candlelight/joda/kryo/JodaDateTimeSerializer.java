package candlelight.joda.kryo;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

/**
 * Serializes a DateTime to mills, preserving the timezone
 */
public class JodaDateTimeSerializer extends Serializer<DateTime> {

    @Override
    public void write(Kryo kryo, Output output, DateTime t) {
        output.writeLong(t.getMillis(), true);
        output.writeString(t.getZone().getID());
    }

    @Override
    public DateTime read(Kryo kryo, Input input, Class<DateTime> type) {
        return new DateTime(input.readLong(true), DateTimeZone.forID(input.readString()));
    }
}
