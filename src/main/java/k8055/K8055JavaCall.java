package k8055;

public class K8055JavaCall {
	
    static {
        System.loadLibrary("K8055JavaCall");
    }

    public native int OpenDevice(long CardAddress);
    public native int CloseDevice();
    public native long ReadAnalogChannel(long Channel);
    public native int ReadAllAnalog(Long Data1, Long Data2);
    public native int OutputAnalogChannel(long Channel, long Data);
    public native int OutputAllAnalog(long Data1, long Data2);
    public native int ClearAnalogChannel(long Channel);
    public native int ClearAllAnalog();
    public native int SetAnalogChannel(long Channel);
    public native int SetAllAnalog();
    public native int WriteAllDigital(long Data);
    public native int ClearDigitalChannel(long Channel);
    public native int ClearAllDigital();
    public native int SetDigitalChannel(long Channel);
    public native int SetAllDigital();
    public native int ReadDigitalChannel(long Channel);
    public native long ReadAllDigital();
    public native long ReadCounter(long CounterNr);
    public native int ResetCounter(long CounterNr);
    public native int SetCounterDebounceTime(long CounterNr, long DebounceTime);
    
}
