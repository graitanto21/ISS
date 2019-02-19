/* Generated by AN DISI Unibo */ 
package it.unibo.initial_conditions_checker;
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
public abstract class AbstractInitial_conditions_checker extends QActor { 
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
		public AbstractInitial_conditions_checker(String actorId, QActorContext myCtx, IOutputEnvView outEnvView )  throws Exception{
			super(actorId, myCtx,  
			"./srcMore/it/unibo/initial_conditions_checker/WorldTheory.pl",
			setTheEnv( outEnvView )  , "init");
			this.planFilePath = "./srcMore/it/unibo/initial_conditions_checker/plans.txt";
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
	    	stateTab.put("checkEvent",checkEvent);
	    	stateTab.put("checkConditions",checkConditions);
	    }
	    StateFun handleToutBuiltIn = () -> {	
	    	try{	
	    		PlanRepeat pr = PlanRepeat.setUp("handleTout",-1);
	    		String myselfName = "handleToutBuiltIn";  
	    		println( "initial_conditions_checker tout : stops");  
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
	    	temporaryStr = "\"initial_conditions_checker STARTED\"";
	    	println( temporaryStr );  
	    	//switchTo waitForEvents
	        switchToPlanAsNextState(pr, myselfName, "initial_conditions_checker_"+myselfName, 
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
	     msgTransition( pr,myselfName,"initial_conditions_checker_"+myselfName,false,
	          new StateFun[]{stateTab.get("checkEvent"), stateTab.get("checkConditions") }, 
	          new String[]{"true","E","modelChanged", "true","E","userstart" },
	          1000000, "handleToutBuiltIn" );//msgTransition
	    }catch(Exception e_waitForEvents){  
	    	 println( getName() + " plan=waitForEvents WARNING:" + e_waitForEvents.getMessage() );
	    	 QActorContext.terminateQActorSystem(this); 
	    }
	    };//waitForEvents
	    
	    StateFun checkEvent = () -> {	
	    try{	
	     PlanRepeat pr = PlanRepeat.setUp("checkEvent",-1);
	    	String myselfName = "checkEvent";  
	    	//onEvent 
	    	setCurrentMsgFromStore(); 
	    	curT = Term.createTerm("modelChanged(resource(name(temperatureIsOk),state(X)))");
	    	if( currentEvent != null && currentEvent.getEventId().equals("modelChanged") && 
	    		pengine.unify(curT, Term.createTerm("modelChanged(resource(NAME,STATE))")) && 
	    		pengine.unify(curT, Term.createTerm( currentEvent.getMsg() ) )){ 
	    			String parg  ="temperatureIsOk(_)";
	    			String parg1 ="temperatureIsOk(X)";
	    			/* ReplaceRule */
	    			parg = updateVars(Term.createTerm("modelChanged(resource(NAME,STATE))"),  Term.createTerm("modelChanged(resource(name(temperatureIsOk),state(X)))"), 
	    				    		  					Term.createTerm(currentEvent.getMsg()), parg);
	    			parg1 = updateVars(Term.createTerm("modelChanged(resource(NAME,STATE))"),  Term.createTerm("modelChanged(resource(name(temperatureIsOk),state(X)))"), 
	    				    		  					Term.createTerm(currentEvent.getMsg()), parg1);
	    			if( parg != null && parg1 != null  ) replaceRule(parg, parg1);	    		  					
	    	}
	    	//onEvent 
	    	setCurrentMsgFromStore(); 
	    	curT = Term.createTerm("modelChanged(resource(name(timeIsOk),state(X)))");
	    	if( currentEvent != null && currentEvent.getEventId().equals("modelChanged") && 
	    		pengine.unify(curT, Term.createTerm("modelChanged(resource(NAME,STATE))")) && 
	    		pengine.unify(curT, Term.createTerm( currentEvent.getMsg() ) )){ 
	    			String parg  ="timeIsOk(_)";
	    			String parg1 ="timeIsOk(X)";
	    			/* ReplaceRule */
	    			parg = updateVars(Term.createTerm("modelChanged(resource(NAME,STATE))"),  Term.createTerm("modelChanged(resource(name(timeIsOk),state(X)))"), 
	    				    		  					Term.createTerm(currentEvent.getMsg()), parg);
	    			parg1 = updateVars(Term.createTerm("modelChanged(resource(NAME,STATE))"),  Term.createTerm("modelChanged(resource(name(timeIsOk),state(X)))"), 
	    				    		  					Term.createTerm(currentEvent.getMsg()), parg1);
	    			if( parg != null && parg1 != null  ) replaceRule(parg, parg1);	    		  					
	    	}
	    	repeatPlanNoTransition(pr,myselfName,"initial_conditions_checker_"+myselfName,false,true);
	    }catch(Exception e_checkEvent){  
	    	 println( getName() + " plan=checkEvent WARNING:" + e_checkEvent.getMessage() );
	    	 QActorContext.terminateQActorSystem(this); 
	    }
	    };//checkEvent
	    
	    StateFun checkConditions = () -> {	
	    try{	
	     PlanRepeat pr = PlanRepeat.setUp("checkConditions",-1);
	    	String myselfName = "checkConditions";  
	    	//onEvent 
	    	setCurrentMsgFromStore(); 
	    	curT = Term.createTerm("userstart(user)");
	    	if( currentEvent != null && currentEvent.getEventId().equals("userstart") && 
	    		pengine.unify(curT, Term.createTerm("userstart(X)")) && 
	    		pengine.unify(curT, Term.createTerm( currentEvent.getMsg() ) )){ 
	    			//println("WARNING: variable substitution not yet fully implemented " ); 
	    			{//actionseq
	    			if( (guardVars = QActorUtils.evalTheGuard(this, " !?startRequirementsOk" )) != null ){
	    			temporaryStr = QActorUtils.unifyMsgContent(pengine, "startRobot","startRobot", guardVars ).toString();
	    			emit( "startRobot", temporaryStr );
	    			}
	    			};//actionseq
	    	}
	    	repeatPlanNoTransition(pr,myselfName,"initial_conditions_checker_"+myselfName,false,true);
	    }catch(Exception e_checkConditions){  
	    	 println( getName() + " plan=checkConditions WARNING:" + e_checkConditions.getMessage() );
	    	 QActorContext.terminateQActorSystem(this); 
	    }
	    };//checkConditions
	    
	    protected void initSensorSystem(){
	    	//doing nothing in a QActor
	    }
	
	}