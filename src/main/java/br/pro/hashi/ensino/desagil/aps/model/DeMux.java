package br.pro.hashi.ensino.desagil.aps.model;

public class DeMux extends Gate {
    NandGate nandA;
    NandGate nandB;
    NandGate nandC;
    NandGate nandD;
    NandGate nandE;

    public DeMux() {
        super("DeMux", 2, 2);
        nandA = new NandGate();
        nandB = new NandGate();
        nandC = new NandGate();
        nandD = new NandGate();
        nandE = new NandGate();

        nandB.connect(1, nandA);

        nandD.connect(0, nandB);
        nandD.connect(1, nandB);

        nandE.connect(0, nandC);
        nandE.connect(1, nandC);
    }

    @Override
    public boolean read(int outputPin) {
        if (outputPin == 0) {
            return nandD.read();
        } else if (outputPin == 1) {
            return nandE.read();
        } else {
            throw new IndexOutOfBoundsException(outputPin);
        }
    }

    @Override
    public void connect(int inputPin, SignalEmitter emitter) {
        switch (inputPin) {
            case 0:
                nandB.connect(0, emitter);
                nandC.connect(1, emitter);
                break;
            case 1:
                nandA.connect(0, emitter);
                nandA.connect(1, emitter);
                nandC.connect(0, emitter);
                break;
            default:
                throw new IndexOutOfBoundsException(inputPin);

        }
    }
}
