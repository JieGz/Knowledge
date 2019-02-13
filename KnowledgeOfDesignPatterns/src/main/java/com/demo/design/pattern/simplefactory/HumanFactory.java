package com.demo.design.pattern.simplefactory;

/**
 * @author 揭光智
 * @date 2019/02/13
 */
public class HumanFactory extends AbstractHumanFactory {

    @Override
    public <T extends Human> T createHuman(Class<T> clazz) {
        Human human = null;
        try {
            human = (T) Class.forName(clazz.getName()).newInstance();
        } catch (Exception e) {
            System.out.println("人种生成错误!");
        }
        return (T) human;
    }
}
