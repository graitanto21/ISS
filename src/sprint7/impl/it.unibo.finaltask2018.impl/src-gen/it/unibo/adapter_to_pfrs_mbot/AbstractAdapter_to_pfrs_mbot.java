/* Generated by AN DISI Unibo */ 
package it.unibo.adapter_to_pfrs_mbot;
import it.unibo.qactors.PlanRepeat;
import it.unibo.qactors.QActorContext;
import it.unibo.qactors.StateExecMessage;
import it.unibo.qactors.QActorUtils;
import it.unibo.is.interfaces.IOutputEnvView;
import it.unibo.qactors.action.AsynchActionResult;
import it.unibo.qactors.action.IActorAction;
import it.unibo.qactors.action.IActorAction.ActionExecMode;
import it.unibo.qactors.action.IMsgQueue;
import it.unibo.qactors.akka.QActor;
import it.unibo.qactors.StateFun;
import java.util.Stack;
import java.util.Hashtable;
import java.util.concurrent.Callable;
import alice.tuprolog.Struct;
import alice.tuprolog.Term;
import it.unibo.qactors.action.ActorTimedAction;
public abstract class AbstractAdapter_to_pfrs_mbot extends QActor { 
	protected AsynchActionResult aar = null;
	protected boolean actionResult = true;
	protected alice.tuprolog.SolveInfo sol;
	protected String planFilePath    = null;
	protected String terminationEvId = "default";
	protected String parg="";
	protected boolean bres=false;
	protected IActorAction action;
	 
	
		protected static IOutputEnvView setTheEnv(IOutputEnvView outEnvView ){
			return outEnvView;
		}
		public AbstractAdapter_to_pfrs_mbot(String actorId, QActorContext myCtx, IOutputEnvView outEnvView )  throws Exception{
			super(actorId, myCtx,  
			"./srcMore/it/unibo/adapter_to_pfrs_mbot/WorldTheory.pl",
			setTheEnv( outEnvView )  , "init");
			this.planFilePath = "./srcMore/it/unibo/adapter_to_pfrs_mbot/plans.txt";
	  	}
		@Override
		protected void doJob() throws Exception {
			String name  = getName().replace("_ctrl", "");
			mysupport = (IMsgQueue) QActorUtils.getQActor( name ); 
			initStateTable(); 
	 		initSensorSystem();
	 		history.push(stateTab.get( "init" ));
	  	 	autoSendStateExecMsg();
	  		//QActorContext.terminateQActorSystem(this);//todo
		} 	
		/* 
		* ------------------------------------------------------------
		* PLANS
		* ------------------------------------------------------------
		*/    
	    //genAkkaMshHandleStructure
	    protected void initStateTable(){  	
	    	stateTab.put("handleToutBuiltIn",handleToutBuiltIn);
	    	stateTab.put("init",init);
	    	stateTab.put("waitMsgs",waitMsgs);
	    	stateTab.put("moveRobot",moveRobot);
	    }
	    StateFun handleToutBuiltIn = () -> {	
	    	try{	
	    		PlanRepeat pr = PlanRepeat.setUp("handleTout",-1);
	    		String myselfName = "handleToutBuiltIn";  
	    		println( "adapter_to_pfrs_mbot tout : stops");  
	    		repeatPlanNoTransition(pr,myselfName,"application_"+myselfName,false,false);
	    	}catch(Exception e_handleToutBuiltIn){  
	    		println( getName() + " plan=handleToutBuiltIn WARNING:" + e_handleToutBuiltIn.getMessage() );
	    		QActorContext.terminateQActorSystem(this); 
	    	}
	    };//handleToutBuiltIn
	    
	    StateFun init = () -> {	
	    try{	
	     PlanRepeat pr = PlanRepeat.setUp("init",-1);
	    	String myselfName = "init";  
	    	temporaryStr = "\"adapter_to_pfrs_mbot STARTS\"";
	    	println( temporaryStr );  
	    	it.unibo.pfrs.mbotConnTcp.initClientConn( myself  );
	    	//switchTo waitMsgs
	        switchToPlanAsNextState(pr, myselfName, "adapter_to_pfrs_mbot_"+myselfName, 
	              "waitMsgs",false, false, null); 
	    }catch(Exception e_init){  
	    	 println( getName() + " plan=init WARNING:" + e_init.getMessage() );
	    	 QActorContext.terminateQActorSystem(this); 
	    }
	    };//init
	    
	    StateFun waitMsgs = () -> {	
	    try{	
	     PlanRepeat pr = PlanRepeat.setUp(getName()+"_waitMsgs",0);
	     pr.incNumIter(); 	
	    	String myselfName = "waitMsgs";  
	    	//bbb
	     msgTransition( pr,myselfName,"adapter_to_pfrs_mbot_"+myselfName,false,
	          new StateFun[]{stateTab.get("moveRobot") }, 
	          new String[]{"true","M","msg_modelChanged" },
	          1000000, "handleToutBuiltIn" );//msgTransition
	    }catch(Exception e_waitMsgs){  
	    	 println( getName() + " plan=waitMsgs WARNING:" + e_waitMsgs.getMessage() );
	    	 QActorContext.terminateQActorSystem(this); 
	    }
	    };//waitMsgs
	    
	    StateFun moveRobot = () -> {	
	    try{	
	     PlanRepeat pr = PlanRepeat.setUp("moveRobot",-1);
	    	String myselfName = "moveRobot";  
	    	//onMsg 
	    	setCurrentMsgFromStore(); 
	    	curT = Term.createTerm("modelChanged(resource(name(robot),state(movement(stopped),X)))");
	    	if( currentMessage != null && currentMessage.msgId().equals("msg_modelChanged") && 
	    		pengine.unify(curT, Term.createTerm("modelChanged(resource(NAME,STATE))")) && 
	    		pengine.unify(curT, Term.createTerm( currentMessage.msgContent() ) )){ 
	    		{/* JavaLikeMove */ 
	    		it.unibo.pfrs.mbotConnTcp.mbotStop(this );
	    		}
	    	}
	    	//onMsg 
	    	setCurrentMsgFromStore(); 
	    	curT = Term.createTerm("modelChanged(resource(name(robot),state(movement(movingForward),obstacleDetected(false))))");
	    	if( currentMessage != null && currentMessage.msgId().equals("msg_modelChanged") && 
	    		pengine.unify(curT, Term.createTerm("modelChanged(resource(NAME,STATE))")) && 
	    		pengine.unify(curT, Term.createTerm( currentMessage.msgContent() ) )){ 
	    		{/* JavaLikeMove */ 
	    		it.unibo.pfrs.mbotConnTcp.mbotForward(this );
	    		}
	    	}
	    	//onMsg 
	    	setCurrentMsgFromStore(); 
	    	curT = Term.createTerm("modelChanged(resource(name(robot),state(movement(movingBackward),X)))");
	    	if( currentMessage != null && currentMessage.msgId().equals("msg_modelChanged") && 
	    		pengine.unify(curT, Term.createTerm("modelChanged(resource(NAME,STATE))")) && 
	    		pengine.unify(curT, Term.createTerm( currentMessage.msgContent() ) )){ 
	    		{/* JavaLikeMove */ 
	    		it.unibo.pfrs.mbotConnTcp.mbotBackward(this );
	    		}
	    	}
	    	//onMsg 
	    	setCurrentMsgFromStore(); 
	    	curT = Term.createTerm("modelChanged(resource(name(robot),state(movement(turningLeft),X)))");
	    	if( currentMessage != null && currentMessage.msgId().equals("msg_modelChanged") && 
	    		pengine.unify(curT, Term.createTerm("modelChanged(resource(NAME,STATE))")) && 
	    		pengine.unify(curT, Term.createTerm( currentMessage.msgContent() ) )){ 
	    		{/* JavaLikeMove */ 
	    		it.unibo.pfrs.mbotConnTcp.mbotLeft(this );
	    		}
	    	}
	    	//onMsg 
	    	setCurrentMsgFromStore(); 
	    	curT = Term.createTerm("modelChanged(resource(name(robot),state(movement(turningRight),X)))");
	    	if( currentMessage != null && currentMessage.msgId().equals("msg_modelChanged") && 
	    		pengine.unify(curT, Term.createTerm("modelChanged(resource(NAME,STATE))")) && 
	    		pengine.unify(curT, Term.createTerm( currentMessage.msgContent() ) )){ 
	    		{/* JavaLikeMove */ 
	    		it.unibo.pfrs.mbotConnTcp.mbotRight(this );
	    		}
	    	}
	    	repeatPlanNoTransition(pr,myselfName,"adapter_to_pfrs_mbot_"+myselfName,false,true);
	    }catch(Exception e_moveRobot){  
	    	 println( getName() + " plan=moveRobot WARNING:" + e_moveRobot.getMessage() );
	    	 QActorContext.terminateQActorSystem(this); 
	    }
	    };//moveRobot
	    
	    protected void initSensorSystem(){
	    	//doing nothing in a QActor
	    }
	
	}
