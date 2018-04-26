package mjc.core.parser;

import mjc.core.parser.interfaces.IAfterDot;
import mjc.core.parser.interfaces.IExpression;
import mjc.core.parser.interfaces.IExpressionItems;

public class ExpressionItems14 implements IExpressionItems{
	//ExpressionItems::= “.” AfterDot
	IAfterDot afterDot;
	
	public ExpressionItems14(IAfterDot afterDot) {
		super();
		this.afterDot = afterDot;
	}

	@Override
		public String getValue() {
			// TODO Auto-generated method stub
			return "."+afterDot.gtValue();
		}
}
