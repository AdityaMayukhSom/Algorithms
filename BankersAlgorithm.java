public class BankersAlgorithm {
    public static void main(String[] args) {
        // MAX Matrix
        int[][] max = new int[][] {
                { 7, 5, 3 }, // P0
                { 3, 2, 2 }, // P1
                { 9, 0, 2 }, // P2
                { 2, 2, 2 }, // P3
                { 4, 3, 3 } }; // P4

        // Allocation Matrix
        int[][] alloc = new int[][] {
                { 0, 1, 0 }, // P0
                { 2, 0, 0 }, // P1
                { 3, 0, 2 }, // P2
                { 2, 1, 1 }, // P3
                { 0, 0, 2 } }; // P4

        // Available Resources
        int[] avail = new int[] { 3, 3, 2 };

        int[][] required = new int[][] {
                { 7, 4, 3 },
                { 1, 2, 2 },
                { 6, 0, 0 },
                { 0, 1, 1 },
                { 4, 3, 1 } };

        BankersAlgorithm bankAlgo = new BankersAlgorithm();
        int[] sequence = bankAlgo.safeStateAlgorithm(max, alloc, avail);
        if (sequence == null) {
            System.out.println("The given sequence is not safe");
        } else {
            System.out.println("Following is the SAFE Sequence");
            for (int processIndex = 0; processIndex < sequence.length - 1; processIndex++)
                System.out.printf("p%d -> ", sequence[processIndex]);
            System.out.printf("p%d\n", sequence[sequence.length - 1]);
        }
    }

    /**
     * Given the maximum required resources, already allocated resources
     * and the currently available resources, we need to determine if the
     * state is safe or not.
     * 
     * <blockquote>
     * 
     * <pre>
     * BankersAlgorithm bankAlgo = new BankersAlgorithm();
     * int[] sequence = bankAlgo.safeStateAlgorithm(max, alloc, avail);
     * if (sequence == null) {
     *     // deadlock can occur, state is unsafe
     * } else {
     *     // state is safe, can allocate resources
     * }
     * </pre>
     * 
     * </blockquote>
     * 
     * @param maximum   A two dimentional array of arrays of size as number of
     *                  processes where each entry at position i contains the
     *                  maximum required resources by process Pi.
     * 
     * @param allocated A two dimentional array of length equal to number of
     *                  processes and each entry at ith position refers to already
     *                  allocated resources to Pi.
     * 
     * @param available A one dimentional array containing number of available
     *                  resources for each type.
     * 
     * @return An array containing the safe state sequence if such a sequence
     *         is possible, otherwise returns {@code null} if no such state is
     *         possible.
     * 
     * @throws IllegalArgumentException
     *                                  if the dimention of {@code maximum} does not
     *                                  match with the dimention of
     *                                  {@code available}, if any one of the
     *                                  parameters are {@code null}, or if length of
     *                                  {@code available} does not match with the
     *                                  array length at any ith position of
     *                                  {@code maximum}.
     * 
     * 
     * @apiNote This assumes that at the worst case, all the processes will request
     *          the maximum required resources at once.
     */
    public int[] safeStateAlgorithm(final int[][] maximum, final int[][] allocated, final int[] available)
            throws IllegalArgumentException {
        if (maximum == null || allocated == null || available == null)
            throw new IllegalArgumentException("arguments to safeStateAlgorithm() cannot be null.");

        if (maximum.length != allocated.length)
            throw new IllegalArgumentException("dimension of 'maximum' and dimention of 'allocated' do not match.");

        if (maximum.length != 0 && maximum[0].length != available.length)
            throw new IllegalArgumentException(
                    "dimension of 'available' does not match with resource count in 'maximum'.");

        // number of processes on which we need to check if the state is safe or not
        int n = maximum.length;

        // array to store the sequence of execusion for the processes
        int[] sequence = new int[n];

        // if there are no processes mentioned, then the sequence is empty array
        if (maximum.length == 0)
            return sequence;

        // number of resources available, not considering multiple instance of same
        // resource, rather multiple instance can be handled as separate resoources.
        int m = available.length;

        // whether ith process has been scheduled or not
        boolean[] finished = new boolean[n];

        // deep copies the available array for modification, so that the available
        // array can remain unmodified
        int[] currentlyAvailable = new int[m];
        for (int resourceID = 0; resourceID < m; resourceID++) {
            currentlyAvailable[resourceID] = available[resourceID];
        }

        // required[i][j] stores the amount of resource of type Rj
        // still required by the process Pi
        int[][] required = new int[n][m];

        for (int processID = 0; processID < n; ++processID) {
            for (int resourceID = 0; resourceID < m; ++resourceID) {
                required[processID][resourceID] = maximum[processID][resourceID] - allocated[processID][resourceID];
            }
        }

        int completedProcesses = 0;

        for (int processCount = 0; processCount < n; processCount++) {
            // ideally if it is a safe state, at each iteration, one process should be
            // satisfied, otherwise it is not a safe state

            boolean isSafe = false;

            for (int processID = 0; processID < n; processID++) {
                if (finished[processID])
                    continue;

                boolean canAllocate = true;

                for (int resourceID = 0; resourceID < m; resourceID++) {
                    if (required[processID][resourceID] > currentlyAvailable[resourceID]) {
                        canAllocate = false;
                        break;
                    }
                }

                if (!canAllocate)
                    continue;

                isSafe = true;
                sequence[completedProcesses++] = processID;
                finished[processID] = true;

                for (int resourceID = 0; resourceID < m; resourceID++) {
                    currentlyAvailable[resourceID] += maximum[processID][resourceID];
                }
            }

            // if no process could be allocated at that turn, then it is not a safe state
            if (!isSafe)
                break;
        }

        return (completedProcesses == n) ? sequence : null;
    }

}