package com.example156;
//�����ǿ���dukeͼƬ�˶�����
public class PicRunThread extends Thread{
	MySurfaceView msv;									//MySurfaceView������
	private float picX=0;			//ͼƬx����
	private float picY=Constant.SCREENHEIGHT-Constant.PICHEIGHT;			//ͼƬy����
	boolean yRunFlag=false;		//y�����ϵ��˶���ǣ�falseʱy=y+speed��trueʱy=y-speed
	int picAlphaNum=0;					//ͼƬ�䰵Ч���л��ʵ�alphaֵ
	public PicRunThread(MySurfaceView msv) {
		super();
		this.msv = msv;			//�����߳��������ָ��������MySurfaceView�Ķ���
	}
	@Override
	public void run() {
		super.run();
		while(true){
			//����dukeͼƬ���˶�
			while(this.picX<Constant.SCREENWIDTH){			//��ͼƬ�������ȫ������Ļ���ұ�ʱ��ѭ������
				msv.setPicX(picX);
				msv.setPicY(picY);
				picX=picX+Constant.PICXSPEED;
				if(yRunFlag){//Ӧ�������˶����Լ�
					picY=picY-Constant.PICYSPEED;
				}else{//Ӧ�������˶����Լ�
					picY=picY+Constant.PICYSPEED;
				}
				if(picY<=0){									//������Ļ����
					yRunFlag=false;
				}else if(picY>Constant.SCREENHEIGHT-Constant.PICHEIGHT){		//������Ļ����
					yRunFlag=true;
				}
				try{
					Thread.sleep(Constant.PICRUNSPEED);
				}catch(Exception e){e.printStackTrace();}
			}
			//ͼƬ�䰵Ч����ʾ
			msv.picAlphaFlag=true;							//����ͼƬ�䰵Ч��
			for(picAlphaNum=0;picAlphaNum<=255;picAlphaNum++){
				if(picAlphaNum==255){
					msv.picAlphaFlag=false;					//��ͼƬ�䰵Ч���������������
					picX=0;			//ͼƬx����
					picY=Constant.SCREENHEIGHT-Constant.PICHEIGHT;			//ͼƬy����
					System.out.println(msv.picAlphaFlag+"picX:"+picX+"picY:"+picY);
				}
				msv.setPicAlphaNum(picAlphaNum);
				try{
					Thread.sleep(Constant.PICALPHASPEED);
				}catch(Exception e){e.printStackTrace();}
			}
		}
	}
}
