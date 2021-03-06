/* Generated by AN DISI Unibo */ 
package it.unibo.stop_conditions_checker;
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
public abstract class AbstractStop_conditions_checker extends QActor { 
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
		public AbstractStop_conditions_checker(String actorId, QActorContext myCtx, IOutputEnvView outEnvView )  throws Exception{
			super(actorId, myCtx,  
			"./srcMore/it/unibo/stop_conditions_checker/WorldTheory.pl",
			setTheEnv( outEnvView )  , "init");
			this.planFilePath = "./srcMore/it/unibo/stop_conditions_checker/plans.txt";
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
	    	stateTab.put("waitForEvents",waitForEvents);
	    	stateTab.put("stopRobot",stopRobot);
	    }
	    StateFun handleToutBuiltIn = () -> {	
	    	try{	
	    		PlanRepeat pr = PlanRepeat.setUp("handleTout",-1);
	    		String myselfName = "handleToutBuiltIn";  
	    		println( "stop_conditions_checker tout : stops");  
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
	    	temporaryStr = "\"stop_conditions_checker STARTED\"";
	    	println( temporaryStr );  
	    	//switchTo waitForEvents
	        switchToPlanAsNextState(pr, myselfName, "stop_conditions_checker_"+myselfName, 
	              "waitForEvents",false, false, null); 
	    }catch(Exception e_init){  
	    	 println( getName() + " plan=init WARNING:" + e_init.getMessage() );
	    	 QActorContext.terminateQActorSystem(this); 
	    }
	    };//init
	    
	    StateFun waitForEvents = () -> {	
	    try{	
	     PlanRepeat pr = PlanRepeat.setUp(getName()+"_waitForEvents",0);
	     pr.incNumIter(); 	
	    	String myselfName = "waitForEvents";  
	    	//bbb
	     msgTransition( pr,myselfName,"stop_conditions_checker_"+myselfName,false,
	          new StateFun[]{stateTab.get("stopRobot"), stateTab.get("stopRobot") }, 
	          new String[]{"true","E","modelChanged", "true","E","userstop" },
	          1000000, "handleToutBuiltIn" );//msgTransition
	    }catch(Exception e_waitForEvents){  
	    	 println( getName() + " plan=waitForEvents WARNING:" + e_waitForEvents.getMessage() );
	    	 QActorContext.terminateQActorSystem(this); 
	    }
	    };//waitForEvents
	    
	    StateFun stopRobot = () -> {	
	    try{	
	     PlanRepeat pr = PlanRepeat.setUp("stopRobot",-1);
	    	String myselfName = "stopRobot";  
	    	//onEvent 
	    	setCurrentMsgFromStore(); 
	    	curT = Term.createTerm("modelChanged(resource(name(temperatureIsOk),state(false)))");
	    	if( currentEvent != null && currentEvent.getEventId().equals("modelChanged") && 
	    		pengine.unify(curT, Term.createTerm("modelChanged(resource(NAME,STATE))")) && 
	    		pengine.unify(curT, Term.createTerm( currentEvent.getMsg() ) )){ 
	    			//println("WARNING: variable substitution not yet fully implemented " ); 
	    			//delay  ( no more reactive within a plan)
	    			aar = delayReactive(1000,"" , "");
	    			if( aar.getInterrupted() ) curPlanInExec   = "stopRobot";
	    			if( ! aar.getGoon() ) return ;
	    	}
	    	//onEvent 
	    	setCurrentMsgFromStore(); 
	    	curT = Term.createTerm("modelChanged(resource(name(timeIsOk),state(false)))");
	    	if( currentEvent != null && currentEvent.getEventId().equals("modelChanged") && 
	    		pengine.unify(curT, Term.createTerm("modelChanged(resource(NAME,STATE))")) && 
	    		pengine.unify(curT, Term.createTerm( currentEvent.getMsg() ) )){ 
	    			//println("WARNING: variable substitution not yet fully implemented " ); 
	    			//delay  ( no more reactive within a plan)
	    			aar = delayReactive(1000,"" , "");
	    			if( aar.getInterrupted() ) curPlanInExec   = "stopRobot";
	    			if( ! aar.getGoon() ) return ;
	    	}
	    	//onEvent 
	    	setCurrentMsgFromStore(); 
	    	curT = Term.createTerm("userstop(user)");
	    	if( currentEvent != null && currentEvent.getEventId().equals("userstop") && 
	    		pengine.unify(curT, Term.createTerm("userstop(X)")) && 
	    		pengine.unify(curT, Term.createTerm( currentEvent.getMsg() ) )){ 
	    			//println("WARNING: variable substitution not yet fully implemented " ); 
	    			//delay  ( no more reactive within a plan)
	    			aar = delayReactive(1000,"" , "");
	    			if( aar.getInterrupted() ) curPlanInExec   = "stopRobot";
	    			if( ! aar.getGoon() ) return ;
	    	}
	    	repeatPlanNoTransition(pr,myselfName,"stop_conditions_checker_"+myselfName,false,true);
	    }catch(Exception e_stopRobot){  
	    	 println( getName() + " plan=stopRobot WARNING:" + e_stopRobot.getMessage() );
	    	 QActorContext.terminateQActorSystem(this); 
	    }
	    };//stopRobot
	    
	    protected void initSensorSystem(){
	    	//doing nothing in a QActor
	    }
	
	}
