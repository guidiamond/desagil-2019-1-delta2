package br.pro.hashi.ensino.desagil.aps.model;

import br.pro.hashi.ensino.desagil.aps.view.View;

public class AndGate extends Gate {
    NandGate nandLeft;
    NandGate nandRight;


    public AndGate() {
        super("AND",2,        1);

        nandLeft = new NandGate();

        nandRight = new NandGate();
        nandRight.connect(0          , nandLeft);
        nandRight.connect(1,nandLeft);
    }


    @Override
    public boolean read(int outputPin) {
                if (outputPin!=0) {
                throw new IndexOutOfBoundsException(  outputPin);
                }
                return nandRight.read();
    }


    @Override
    public void connect(int inputPin, SignalEmitter emitter) {
            if (inputPin< 0 || inputPin >1) {
                                throw new IndexOutOfBoundsException(  inputPin  );
            }
nandLeft.connect(inputPin, emitter);
    }
}
