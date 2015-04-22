package k8055;

import k8055.K8055JavaCall;

public class TestK8055 {

	public static void main(String[] args)  throws Exception
    {
        K8055JavaCall k8055 = new K8055JavaCall();
        
        try {
        	k8055.OpenDevice(0);
			int x = 0;
			for (int i = 0; i < 50; i++) {
				x++;
				if (x == 8) {
					x = 0;
				}
				display(k8055, x);
				Thread.sleep(250);
			}
			k8055.ClearAllDigital();
        	k8055.CloseDevice();
        } catch (Exception e){
        	e.printStackTrace();
        }
    }
	
	private static void display(K8055JavaCall k8055, int number){
		k8055.ClearAllDigital();
		
		if(number >= 4){
			k8055.SetDigitalChannel(3);
			number -= 4;
		}
		
		if(number >= 2){
			k8055.SetDigitalChannel(2);
			number -= 2;
		}
		
		if(number >= 1){
			k8055.SetDigitalChannel(1);
			number -= 1;
		}
	}
	
}

