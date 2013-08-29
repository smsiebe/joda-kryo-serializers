package candlelight.joda.kryo;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import org.joda.time.LocalDate;

/**
 *
 */
public class JodaLocalDateSerializer extends Serializer<LocalDate> {

    @Override
    public void write(Kryo kryo, Output output, LocalDate t) {
        output.writeInt(t.getYear(), true);
        output.writeInt(t.getMonthOfYear(), true);
        output.writeInt(t.getDayOfMonth(), true);
    }

    @Override
    public LocalDate read(Kryo kryo, Input input, Class<LocalDate> type) {
        return new LocalDate(input.readInt(true), input.readInt(true), input.readInt(true));
    }
}
