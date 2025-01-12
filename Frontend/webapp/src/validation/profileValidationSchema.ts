import * as yup from 'yup';

const profileValidationSchema = yup.object().shape({
    name: yup.string().required('Name is required').min(3, 'Name must be atleast 3 characters'),
    email: yup.string().required('Email is required').email('Email is not valid'),
    password: yup.string().required('Password is required').min(5, 'Password must be atleast 5 characters'),
    confirmPassword: yup.string().required('Retype confirm password').oneOf([yup.ref('password')], 'Password must match')
})

export default profileValidationSchema;