package application.functions;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import net.objecthunter.exp4j.ValidationResult;

public class FunctionCalculator
{
    private String function;

    public FunctionCalculator(String function)
    {
        this.function = function;
    }

    public String getFunction()
    {
        return function;
    }

    public void setFunction(String function)
    {
        this.function = function;
    }

    public Double calculate(double x)
    {
        try
        {
            Expression expression = new ExpressionBuilder(function).variable("x").build();
            ValidationResult validationResult = expression.validate(false);

            if(validationResult.isValid())
            {
                expression.setVariable("x", x);
                return expression.evaluate();
            }
        }
        catch(IllegalArgumentException e)
        {
            return null;
        }
        return null;
    }
}