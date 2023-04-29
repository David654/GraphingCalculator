package application.functions;

import com.jogamp.opengl.GL2;

import java.awt.*;
import java.util.Collections;
import java.util.HashMap;

public class Function
{
    public static final double PRECISION = 0.1;

    private String function;
    private final HashMap<Double, Double> map;
    private final FunctionCalculator calculator;
    private Color color;

    public Function(String function, Color color)
    {
        this.function = function;
        this.color = color;
        map = new HashMap<>();
        calculator = new FunctionCalculator(function);
    }

    public String getFunction()
    {
        return function;
    }

    public void setFunction(String function)
    {
        this.function = function;
        map.clear();
        calculator.setFunction(function);
    }

    public Color getColor()
    {
        return color;
    }

    public void setColor(Color color)
    {
        this.color = color;
    }

    public int getMapSize()
    {
        return map.size();
    }

    public Double getY(double x)
    {
        if(map.containsKey(x))
        {
            return map.get(x);
        }
        return null;
    }

    public double getMinValue()
    {
        return Collections.min(map.keySet());
    }

    public double getMaxValue()
    {
        return Collections.max(map.keySet());
    }

    public double[] getRange()
    {
        return new double[] {getMinValue(), getMaxValue()};
    }

    public void addValue(double x)
    {
        Double y = calculator.calculate(x);
        if(y != null)
        {
            map.put(x, y);
        }
    }

    public void addValuesInRange(double lowerBound, double upperBound, double step)
    {
        map.clear();

        for(double x = lowerBound; x < upperBound; x += step)
        {
            addValue(x);
        }
    }

    public void draw(GL2 gl)
    {

    }
}